import common;

struct QuadTree{
    alias P2 = Point!2;
    Node root;

    //constructor
    this(P2[] points){
        AABB!2 box = boundingBox!2(points);
        root = new Node(points, box);

    }

    class Node{
        bool isLeaf = false;
        P2[] nodePoints;
        AABB!2 box;
        Node[] children;
        int maxPoints = 4;
        

        this(P2[] points, AABB!2 bounding){

            if(points.length <= maxPoints){
                isLeaf = true;
                nodePoints = points;
                box = bounding;
            }else{
                //partitionByDimension
                float xHalf = (bounding.min[0] + bounding.max[0])/2;
                float yHalf = (bounding.min[1] + bounding.max[1])/2;

                P2 midPoint = P2([xHalf, yHalf]);

                P2[] rightHalf = partitionByDimension!(0, 2)(points, xHalf);
                P2[] leftHalf = points[0 .. $  - rightHalf.length];

                //partition into upper/lower left/right
                P2[] upperRight = partitionByDimension!(1,2)(rightHalf, yHalf);
                // writeln("upper right: ", upperRight);
                P2[] lowerRight = rightHalf[0 .. $ - upperRight.length];
                // writeln("lower right: ", lowerRight);

                P2[] upperLeft = partitionByDimension!(1, 2)(leftHalf, yHalf);
                // writeln("upper left: ", upperLeft);
                P2[] lowerLeft = leftHalf[0 .. $ - upperLeft.length];
                // writeln("lower left: ", lowerLeft);

                //getting bounding box areas?

                P2 midLeftPt = P2([bounding.min[0], yHalf]);
                P2 midUpperPt = P2([xHalf, bounding.max[1]]);

                P2 upRightPt = P2([midPoint[0], bounding.max[1]]);

                P2 maxMaxPt = P2([bounding.max[0], bounding.max[1]]);

                P2 minMinPt = P2([bounding.min[0], bounding.min[1]]);

                P2 lowerMidPt = P2([xHalf, bounding.min[1]]);
                P2 midRightPt = P2([bounding.max[0], yHalf]); 
                

                //dividing into 4 boxes
                AABB!2 upperRightBox = boundingBox!2([midPoint, maxMaxPt]);
                AABB!2 lowerRightBox = boundingBox!2([lowerMidPt, midRightPt]);
                AABB!2 upperLeftBox = boundingBox!2([midLeftPt, midUpperPt]);
                AABB!2 lowerLeftBox = boundingBox!2([minMinPt, midPoint]);

                //recursively calling Node constructor
                children ~= new Node(upperRight, upperRightBox);
                children ~= new Node(lowerRight, lowerRightBox);
                children ~= new Node(upperLeft, upperLeftBox);
                children ~= new Node(lowerLeft, lowerLeftBox);
                
            }
        }
    }
    P2[] rangeQuery( P2 point, float radius ){
        P2[] ret;
        //base case is leaf node, check all the points within bounds
        void recurse(Node n){
            if(n.isLeaf){
                foreach(P2 p; n.nodePoints){
                    if(distance(p, point) <= radius){
                        ret ~= p;
                    }
                }
            }else{
                //check each child node and recurse
                //check closest point, if the closest point is out of range of the radius, we do not need to recurse
                foreach(Node child; n.children){
                    P2 closestPoint = closest!2(child.box, point);
                    if(distance(closestPoint, point) <= radius){
                        recurse(child);
                    }
                }
            }
        }
        recurse( root );
        return ret;
        
    }

    //KNNQuery method
    P2[] KNNQuery( P2 point, int K){
            auto priorityQueue = makePriorityQueue!2(point);
            void recurse(Node n){
                //base case
                if(n.isLeaf){
                    foreach(P2 p; n.nodePoints){
                    if(priorityQueue.length < K){
                        priorityQueue.insert(p);
                    //if our priority queue has K points stored, check if this point is closer than the farthest point
                    }else if(distance(p, point) < distance(priorityQueue.front, point)){
                        priorityQueue.popFront;
                        priorityQueue.insert(p);
                    }
                }
                }else{
                    foreach(Node child; n.children){
                    P2 closestPoint = closest!2(child.box, point);
                        //if the priority queu length is less than K or if the distance from the closest point to point is less than the distance from the
                        //worst point in the priority queue to the point
                        if(priorityQueue.length < K || (distance(closestPoint, point) < (distance(priorityQueue.front, point)))){
                            recurse(child);
                        }
                    }
                }
            }
        recurse( root );
        return priorityQueue.release;
    }

}


unittest{
    writeln("QuadTree unit test 1: ");
    auto points = [Point!2([.5, .5]), Point!2([1, 1]),
                   Point!2([0.75, 0.4]), Point!2([0.4, 0.74])];

    auto qt = QuadTree(points);

    writeln("   Range query:");
    foreach(p; qt.rangeQuery(Point!2([1,1]), .7)){
        writeln(p);
    }
    assert(qt.rangeQuery(Point!2([1,1]), .7).length == 3);

    writeln("   KNN");
    foreach(p; qt.KNNQuery(Point!2([1,1]), 3)){
        writeln(p);
    }
}

unittest{
    writeln("QuadTree unit test 2: ");
    auto points = [Point!2([0,0]), Point!2([2.5,0]), Point!2([5,0]), Point!2([7.5,0]), Point!2([10,0]),
                   Point!2([0,2.5]), Point!2([2.5,2.5]), Point!2([5, 2.5]) ,Point!2([7.5,2.5]), Point!2([10, 2.5]),
                   Point!2([0,5]), Point!2([2.5,5]), Point!2([5, 5]) ,Point!2([7.5, 5]), Point!2([10, 5]),
                   Point!2([0,7.5]), Point!2([2.5,7.5]), Point!2([5, 7.5]) ,Point!2([7.5, 7.5]), Point!2([10, 7.5]),
                   Point!2([0, 10]), Point!2([2.5, 10]), Point!2([5, 10]) ,Point!2([7.5, 10]), Point!2([10, 10])];

    auto qt = QuadTree(points);
    auto pointsInRadius = qt.rangeQuery(Point!2([3, 3]), 3);
    writeln("The points in range are: ", pointsInRadius);
    writeln("Done!");
}