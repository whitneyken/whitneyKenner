
import std.stdio;
import std.math;
import std.algorithm;
import std.range;

import common;

struct BucketKNN(size_t Dim){

    alias Bucket = Point!Dim[];
    
    Bucket[] buckets;
    int nDivisions; //how many buckets along each dimension.  #buckets = nDiv^Dim
    Point!Dim minCorner;
    Point!Dim bucketSize;

    this(Point!Dim[] points, int nDivisions){
        this.nDivisions = nDivisions;
        auto aabb = boundingBox(points);
        minCorner = aabb.min;
        bucketSize = (aabb.max - aabb.min)/nDivisions;

        buckets = new Bucket[pow(nDivisions, Dim)];

        foreach(const ref point; points){
            buckets[getIndex(getIndices(point))] ~= point;
        }
    }

    Indices!Dim getIndices(Point!Dim p){
        Indices!Dim ret;
        foreach(i; 0..Dim){
            ret[i] = cast(size_t)clamp(cast(int)( (p[i] - minCorner[i])/bucketSize[i]), 0, nDivisions - 1);
        }
        return ret;
    }

    size_t getIndex(Indices!Dim ind){
        size_t ret = 0;
        foreach(i, x; ind){
            ret += x*pow(nDivisions, Dim - i - 1);
        }
        return ret;
    }


    Point!Dim[] rangeQuery(Point!Dim p, float r){
        auto bottomCorner = p - r;
        auto topCorner = p + r;
        auto startBucket = getIndices(bottomCorner);
        auto stopBucket = getIndices(topCorner);

        Point!Dim[] ret;
        foreach(bIndices; getIndicesRange(startBucket, stopBucket)){
            foreach(const ref q; buckets[getIndex(bIndices)]){
                if(distance(p, q) < r)
                    ret ~= q;
            }
        }
        return ret;
    }

    Point!Dim[] knnQuery(Point!Dim p, int k){
        auto r = maxElement(bucketSize[]);
        auto ret = rangeQuery(p, r);

        int iter = 0;
        while(ret.length < k && iter++ < 10){
            r *= 2;
            ret = rangeQuery(p, r);
        }

        ret.sortByDistance(p);
        ret = ret[0.. k];

        return ret;
    }
    
}


unittest{
    auto bknn = BucketKNN!2([Point!2([.5, .5]), Point!2([1, 1]),
                             Point!2([0.75, 0.4]), Point!2([0.4, 0.74])],
                            2);
    
    writeln(bknn);
    foreach(x; getIndicesRange(Indices!2([0,0]), Indices!2([1, 1]))){
        writeln(x);
    }

    writeln("bucket range query");
    foreach(p; bknn.rangeQuery(Point!2([1,1]), .7)){
        writeln(p);
    }
    writeln("bucket knn");
    foreach(p; bknn.knnQuery(Point!2([1,1]), 3)){
        writeln(p);
    }
}
