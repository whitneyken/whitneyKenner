import common;

struct KDTree(size_t dim){
    alias Pt = Point!dim;
    Node!0 root;

    this(Pt[] points){
        root = new Node!0(points);
    }

    class Node(size_t splitDim){
        enum currentLevel = splitDim;
        enum nextLevel = (splitDim +1 ) % dim;
        public Node!nextLevel leftChild, rightChild;
        public Pt splitPoint;

        this(Pt[] points){
                    // writeln(" ");
                    // writeln("In Node constructor: ");
                    // writeln("");
            //median by dimension sorts by values on that dimension and will return from 0 up to and including the median (left half)
            //once you have this, you know what the split point is and you have the left half, you can get the right half from the rest 
            //and then make node!nextlevel for left and right nodes with their half of the points
                    // writeln("The length of the whole points in this node is: ", points.length);
            if(points.length == 1){
                splitPoint = points[0];
                        // writeln("   In LEAF node: ");
                        // writeln("       This split point is: ", splitPoint);
                        // writeln("       and the level is: ", currentLevel);
            }else{
                //handle median by dimension
                        // writeln("THE POINTS BEFORE: ", points);
                        // writeln("length: ", points.length);
                Pt[] leftHalf = medianByDimension!(currentLevel, dim)(points);
                        // writeln("THE POINTS after: ", points);
                        // writeln("   LEFT half stats: ");
                        // writeln("       length: ", leftHalf.length);
                splitPoint = points[leftHalf.length];
                        // writeln("       split point: ", splitPoint);
                        // writeln("       points: ", leftHalf);
                leftChild = new Node!nextLevel(leftHalf);
                        //If points[] is longer than 3, there will be a right node
                if (points.length > 2){
                            // writeln("   RIGHT half stats");
                            // writeln("       length: ",  points[leftHalf.length + 1.. $].length);
                            // writeln("       points: ", points[leftHalf.length + 1 .. $]);
                    rightChild = new Node!nextLevel(points[leftHalf.length + 1 .. $]);
                }
            }
            
        }
    }

    Pt[] rangeQuery( Pt p, float r ){
    Pt[] ret;
    void recurse( NodeType )( NodeType n ){
        if (distance(p, n.splitPoint) <= r){
            ret ~= n.splitPoint;
        }
        if(n.leftChild !is null && p[n.nextLevel] - r <= n.leftChild.splitPoint[n.nextLevel]){
            recurse(n.leftChild);
        }
        if(n.rightChild !is null && p[n.nextLevel] + r >= n.rightChild.splitPoint[n.nextLevel]){
            recurse(n.rightChild);
        }
    }   
    recurse( root );
    return ret;
    }


    Pt[] KNNQuery( Pt point, int K){
            auto priorityQueue = makePriorityQueue!dim(point);
            AABB!dim bbox;
            bbox.min[] = -float.infinity;
            bbox.max[] = float.infinity;
            void recurse(NodeType)(NodeType n, AABB!dim boxy){
                if(priorityQueue.length < K){
                    priorityQueue.insert(n.splitPoint);
                }else if(distance(n.splitPoint, point) < distance(priorityQueue.front, point)){
                    priorityQueue.insert(n.splitPoint);
                    priorityQueue.popFront;
                }
                AABB!dim leftbbox = boxy;
                leftbbox.max[n.currentLevel] = n.splitPoint[n.currentLevel];

                AABB!dim rightbbox = boxy;
                rightbbox.min[n.currentLevel] = n.splitPoint[n.currentLevel];

                if(n.leftChild !is null && (priorityQueue.length < K || distance(closest(leftbbox, point), point) < distance(priorityQueue.front, point))){
                    recurse(n.leftChild, leftbbox);
                }
                if(n.rightChild !is null && (priorityQueue.length < K || distance(closest(rightbbox, point), point) < distance(priorityQueue.front, point))){
                    recurse(n.rightChild, rightbbox);
                }
            }
        recurse( root, bbox );
        return priorityQueue.release;
    }
}





unittest{
    writeln("KDTree unit test 1: ");
    auto points = [Point!2([0,0]), Point!2([1, 1]), Point!2([2,2])];
    auto testKDTree = KDTree!2(points);
    assert(testKDTree.rangeQuery(Point!2([2,3]), 5.0).length == 3);
}


unittest{
    writeln("KDTree unit test 2: ");
    auto points = [Point!2([0,0]), Point!2([1, 1]), Point!2([2,2])];
    auto testKDTree = KDTree!2(points);
    auto returned = testKDTree.KNNQuery(Point!2([0.5, 0.5]), 2);
    assert(returned.length == 2);
}

alias Pt = Point!2;
unittest{
    writeln("KDTree unit test 3: ");
    auto kdtree = KDTree!2([Pt([0, 1]), Pt([0, 2]), Pt([0, 3]), Pt([0, 4]), Pt([0, 5])]);
    auto nearest_neighbor2D = kdtree.KNNQuery(Pt([0, 0]), 1);
    auto nearest_neighbors2D = kdtree.KNNQuery(Pt([0, 0]), 2);
    writeln("KDtree nearest neighbor2D:", nearest_neighbor2D);
    writeln("KDtree nearest neighbors2D:", nearest_neighbors2D);
    assert(nearest_neighbor2D.length == 1);
    assert(nearest_neighbor2D[0] == Pt([0, 1]));
    assert(nearest_neighbors2D.length == 2);
    assert(nearest_neighbors2D[0] == Pt([0, 2]));
}

unittest{
    writeln("KDTree unit test 4: ");
    auto kdtree = KDTree!2([Pt([1, 0]), Pt([2, 0]), Pt([3, 0]), Pt([4, 0]), Pt([5, 0])]);
    auto nearest_neighbor2D = kdtree.KNNQuery(Pt([0, 0]), 1);
    auto nearest_neighbors2D = kdtree.KNNQuery(Pt([0, 0]), 2);
    writeln("KDtree nearest neighbor2D:", nearest_neighbor2D);
    writeln("KDtree nearest neighbors2D:", nearest_neighbors2D);
    assert(nearest_neighbor2D.length == 1);
    assert(nearest_neighbor2D[0] == Pt([1, 0]));
    assert(nearest_neighbors2D.length == 2);
    assert(nearest_neighbors2D[0] == Pt([2, 0]));
}

unittest{
    writeln("KDTree unit test 5: ");
    auto kdtree = KDTree!2([Pt([1, 1]), Pt([2, 2]), Pt([3, 3]), Pt([4, 4]), Pt([5, 5])]);
    auto nearest_neighbor2D = kdtree.KNNQuery(Pt([0, 0]), 1);
    auto nearest_neighbors2D = kdtree.KNNQuery(Pt([0, 0]), 2);
    writeln("KDtree nearest neighbor2D:", nearest_neighbor2D);
    writeln("KDtree nearest neighbors2D:", nearest_neighbors2D);
    assert(nearest_neighbor2D.length == 1);
    assert(nearest_neighbor2D[0] == Pt([1, 1]));
    assert(nearest_neighbors2D.length == 2);
    assert(nearest_neighbors2D[0] == Pt([2, 2]));
}

unittest{
    writeln("KDTree unit test 6: ");
    auto points = [Point!2([.5, .5]), Point!2([1, 1]),
                   Point!2([0.75, 0.4]), Point!2([0.4, 0.74])];

    auto kt = KDTree!2(points);

    writeln("   Range query:");
    foreach(p; kt.rangeQuery(Point!2([1,1]), .7)){
        writeln(p);
    }
    assert(kt.rangeQuery(Point!2([1,1]), .7).length == 3);

    writeln("   KNN");
    foreach(p; kt.KNNQuery(Point!2([1,1]), 3)){
        writeln(p);
    }
}

unittest{

    auto miniPointsKD = [Point!2([5, 5]) ,Point!2([7.5, 5]), Point!2([10, 5]), Point!2([0,7.5]), Point!2([2.5,7.5])];
    writeln("The length of the points is: ", miniPointsKD.length);
    auto ktMini = KDTree!2(miniPointsKD);

    writeln("The root is: ", ktMini.root.splitPoint);
    writeln("the right child is: ", ktMini.root.rightChild.splitPoint);
    writeln("The left child is: ", ktMini.root.leftChild.splitPoint);

    writeln("The left left child is: ", ktMini.root.leftChild.leftChild.splitPoint);
    writeln("the right left child is: ", ktMini.root.rightChild.leftChild.splitPoint);


    auto answer = ktMini.rangeQuery(Point!2([5, 5]), 3);
    writeln("Answer= ", answer);
    writeln("Done");

    auto points = [Point!2([0,0]), Point!2([1, 1]), Point!2([2,2])];
    auto testKDTree = KDTree!2(points);
    assert(testKDTree.rangeQuery(Point!2([2,3]), 5.0).length == 3);
}