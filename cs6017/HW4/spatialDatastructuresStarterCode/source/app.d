import std.stdio;
import common;
import dumbknn;
import bucketknn;
import quadtree;
import kdtree;
import std.conv;
//import your files here

void main()
{
    //prepare file to write to
    File file = File("timing.csv", "a");
    file.writeln("testType,structType,kVal,nVal,dVal,distribution,time");
    
      

    //initially setting up to test Gaussian with K changing
    //K values I want to test on
    int[] kVals = [5, 10, 25, 50, 100];
    int numRepsToAvg = 50;
    int numTimingReps = 1;
    int[] nVals = [1000, 2000, 5000, 10000, 20000];
    int defaultK = 25;

    //Bucket KNN
    writeln("BucketKNN results");

    //GAUSSIAN points
    writeln("");
    //varying K values
    writeln("varying K values");
    foreach(k; kVals){
        writeln("k = ", k);
        for(int reps = 0; reps < numTimingReps; reps++){
        //will want to repeat and average
            long totalTime = 0;     
            auto trainingPoints = getGaussianPoints!2(1000);
            auto testingPoints = getUniformPoints!2(100);
            auto kd = BucketKNN!2(trainingPoints, cast(int)pow(1000/64, 1.0/2));
            auto sw = StopWatch(AutoStart.no);
            for(int round = 0; round < numRepsToAvg; round++){
                sw.start; //start my stopwatch
                foreach(const ref qp; testingPoints){
                    kd.knnQuery(qp, k);
                }
                sw.stop;
                totalTime+= sw.peek.total!"usecs";  //add the time elapsed in microseconds
            //NOTE, I SOMETIMES GOT TOTALLY BOGUS TIMES WHEN TESTING WITH DMD
            //WHEN YOU TEST WITH LDC, YOU SHOULD GET ACCURATE TIMING INFO...
            }
            long averageTime = totalTime/numRepsToAvg;
            //file.writeln("bucket,"~(to!string(int)(k)) ~",1000,2,G,"~(to!string(averageTime)));
            file.writeln("k,bucket,"~(to!string(k)) ~",1000,2,G,"~(to!string(averageTime)));
            writeln("The average time for ", k, "number of neighbors is: ", averageTime);
        }
    }

    //varying N values
    writeln("varying N values");

    foreach(n; nVals){
        writeln("n = ", n);
        for(int reps = 0; reps < numTimingReps; reps++){
            //will want to repeat and average
            long totalTime = 0;
            auto trainingPoints = getGaussianPoints!2(n);
            auto testingPoints = getUniformPoints!2(n/100);
            auto kd = BucketKNN!2(trainingPoints, cast(int)pow(1000/64, 1.0/2));
            auto sw = StopWatch(AutoStart.no);
            for(int round = 0; round < numRepsToAvg; round++){
                sw.start; //start my stopwatch
                foreach(const ref qp; testingPoints){
                    kd.knnQuery(qp, defaultK);
                }
                sw.stop;
                totalTime+= sw.peek.total!"usecs";  //add the time elapsed in microseconds
            //NOTE, I SOMETIMES GOT TOTALLY BOGUS TIMES WHEN TESTING WITH DMD
            //WHEN YOU TEST WITH LDC, YOU SHOULD GET ACCURATE TIMING INFO...
            }
            long averageTime = totalTime/numRepsToAvg;
            file.writeln("n,bucket,25,"~(to!string(n))~",2,G,"~(to!string(averageTime)));
            writeln("The average time for a training size of ", n, " is: ", averageTime);
        }
    }
    //varying D values
    writeln("varying D values");
    //Same tests for the BucketKNN
    static foreach(dim; 1..8){{
        for(int reps = 0; reps < numTimingReps; reps++){
            long totalTime = 0;
            //get points of the appropriate dimension
            enum numTrainingPoints = 1000;
            auto trainingPoints = getGaussianPoints!dim(numTrainingPoints);
            auto testingPoints = getUniformPoints!dim(100);
            auto kd = BucketKNN!dim(trainingPoints, cast(int)pow(numTrainingPoints/64, 1.0/dim)); //rough estimate to get 64 points per cell on average
            writeln("tree of dimension ", dim, " built");
            auto sw = StopWatch(AutoStart.no);
            for(int round = 0; round < numRepsToAvg; round++){
                sw.start; //start my stopwatch
                foreach(const ref qp; testingPoints){
                    kd.knnQuery(qp, 10);
                }
                sw.stop;
                totalTime+= sw.peek.total!"usecs";
            }
            long averageTime = totalTime/numRepsToAvg;
            file.writeln("d,bucket,25,1000,"~(to!string(dim))~",G,"~(to!string(averageTime)));
            writeln("The average time for a dimension of ", dim, " is: ", averageTime);
    
        }
    }}

    //varying K + N together!
    writeln("varying both K + N");
    for(int index = 0; index < kVals.length; index++){
        int k = kVals[index];
        int n = nVals[index];
        writeln("k = ", k, " n = ", n);
        for(int reps = 0; reps < numTimingReps; reps++){
        //will want to repeat and average
            long totalTime = 0;     
            auto trainingPoints = getGaussianPoints!2(n);
            auto testingPoints = getUniformPoints!2(n/100);
            auto kd = BucketKNN!2(trainingPoints, cast(int)pow(1000/64, 1.0/2));
            auto sw = StopWatch(AutoStart.no);
            for(int round = 0; round < numRepsToAvg; round++){
                sw.start; //start my stopwatch
                foreach(const ref qp; testingPoints){
                    kd.knnQuery(qp, k);
                }
                sw.stop;
                totalTime+= sw.peek.total!"usecs";  //add the time elapsed in microseconds
            //NOTE, I SOMETIMES GOT TOTALLY BOGUS TIMES WHEN TESTING WITH DMD
            //WHEN YOU TEST WITH LDC, YOU SHOULD GET ACCURATE TIMING INFO...
            }
            long averageTime = totalTime/numRepsToAvg;
            file.writeln("kn,bucket,"~(to!string(k))~","~(to!string(n))~",2,G,"~(to!string(averageTime)));
            writeln("The average time for ", k, " number of neighbors and ", n, " size of training set is: ", averageTime);
        }
    }

    //UNIFORM POINTS
    writeln("");
    //varying K values
    writeln("varying K values");
    foreach(k; kVals){
        writeln("k = ", k);
        for(int reps = 0; reps < numTimingReps; reps++){
        //will want to repeat and average
            long totalTime = 0;     
            auto trainingPoints = getUniformPoints!2(1000);
            auto testingPoints = getUniformPoints!2(100);
            auto kd = BucketKNN!2(trainingPoints, cast(int)pow(1000/64, 1.0/2));
            auto sw = StopWatch(AutoStart.no);
            for(int round = 0; round < numRepsToAvg; round++){
                sw.start; //start my stopwatch
                foreach(const ref qp; testingPoints){
                    kd.knnQuery(qp, k);
                }
                sw.stop;
                totalTime+= sw.peek.total!"usecs";  //add the time elapsed in microseconds
            //NOTE, I SOMETIMES GOT TOTALLY BOGUS TIMES WHEN TESTING WITH DMD
            //WHEN YOU TEST WITH LDC, YOU SHOULD GET ACCURATE TIMING INFO...
            }
            long averageTime = totalTime/numRepsToAvg;
            file.writeln("k,bucket,"~(to!string(k)) ~",1000,2,U,"~(to!string(averageTime)));
            writeln("The average time for ", k, "number of neighbors is: ", averageTime);
        }
    }

    //varying N values
    writeln("varying N values");

    foreach(n; nVals){
        writeln("n = ", n);
        for(int reps = 0; reps < numTimingReps; reps++){
            //will want to repeat and average
            long totalTime = 0;
            auto trainingPoints = getUniformPoints!2(n);
            auto testingPoints = getUniformPoints!2(n/100);
            auto kd = BucketKNN!2(trainingPoints, cast(int)pow(1000/64, 1.0/2));
            auto sw = StopWatch(AutoStart.no);
            for(int round = 0; round < numRepsToAvg; round++){
                sw.start; //start my stopwatch
                foreach(const ref qp; testingPoints){
                    kd.knnQuery(qp, defaultK);
                }
                sw.stop;
                totalTime+= sw.peek.total!"usecs";  //add the time elapsed in microseconds
            //NOTE, I SOMETIMES GOT TOTALLY BOGUS TIMES WHEN TESTING WITH DMD
            //WHEN YOU TEST WITH LDC, YOU SHOULD GET ACCURATE TIMING INFO...
            }
            long averageTime = totalTime/numRepsToAvg;
            file.writeln("n,bucket,25,"~(to!string(n))~",2,U,"~(to!string(averageTime)));
            writeln("The average time for a training size of ", n, " is: ", averageTime);
        }
    }
    //varying D values
    writeln("varying D values");
    //Same tests for the BucketKNN
    static foreach(dim; 1..8){{
        for(int reps = 0; reps < numTimingReps; reps++){
            long totalTime = 0;
            //get points of the appropriate dimension
            enum numTrainingPoints = 1000;
            auto trainingPoints = getUniformPoints!dim(numTrainingPoints);
            auto testingPoints = getUniformPoints!dim(100);
            auto kd = BucketKNN!dim(trainingPoints, cast(int)pow(numTrainingPoints/64, 1.0/dim)); //rough estimate to get 64 points per cell on average
            writeln("tree of dimension ", dim, " built");
            auto sw = StopWatch(AutoStart.no);
            for(int round = 0; round < numRepsToAvg; round++){
                sw.start; //start my stopwatch
                foreach(const ref qp; testingPoints){
                    kd.knnQuery(qp, 10);
                }
                sw.stop;
                totalTime+= sw.peek.total!"usecs";
            }
            long averageTime = totalTime/numRepsToAvg;
            file.writeln("d,bucket,25,1000,"~(to!string(dim))~",U,"~(to!string(averageTime)));
            writeln("The average time for a dimension of ", dim, " is: ", averageTime);
    
        }
    }}

    //varying K + N together!
    writeln("varying both K + N");
    for(int index = 0; index < kVals.length; index++){
        int k = kVals[index];
        int n = nVals[index];
        writeln("k = ", k, " n = ", n);
        for(int reps = 0; reps < numTimingReps; reps++){
        //will want to repeat and average
            long totalTime = 0;     
            auto trainingPoints = getUniformPoints!2(n);
            auto testingPoints = getUniformPoints!2(n/100);
            auto kd = BucketKNN!2(trainingPoints, cast(int)pow(1000/64, 1.0/2));
            auto sw = StopWatch(AutoStart.no);
            for(int round = 0; round < numRepsToAvg; round++){
                sw.start; //start my stopwatch
                foreach(const ref qp; testingPoints){
                    kd.knnQuery(qp, k);
                }
                sw.stop;
                totalTime+= sw.peek.total!"usecs";  //add the time elapsed in microseconds
            //NOTE, I SOMETIMES GOT TOTALLY BOGUS TIMES WHEN TESTING WITH DMD
            //WHEN YOU TEST WITH LDC, YOU SHOULD GET ACCURATE TIMING INFO...
            }
            long averageTime = totalTime/numRepsToAvg;
            file.writeln("kn,bucket,"~(to!string(k))~","~(to!string(n))~",2,U,"~(to!string(averageTime)));
            writeln("The average time for ", k, " number of neighbors and ", n, " size of training set is: ", averageTime);
        }
    }

    //QUAD TREE
    //GAUSSIAN DISTRIBUTION
    writeln("QuadTree");
    writeln("Gaussian distribution");
    //varying K values
    writeln("varying K values");
    foreach(k; kVals){
        writeln("k = ", k);
        for(int reps = 0; reps < numTimingReps; reps++){
        //will want to repeat and average
            long totalTime = 0;     
            auto trainingPoints = getGaussianPoints!2(1000);
            auto testingPoints = getUniformPoints!2(100);
            auto qt = QuadTree(trainingPoints);
            auto sw = StopWatch(AutoStart.no);
            for(int round = 0; round < numRepsToAvg; round++){
                sw.start; //start my stopwatch
                foreach(const ref qp; testingPoints){
                    qt.KNNQuery(qp, k);
                }
                sw.stop;
                totalTime+= sw.peek.total!"usecs";  //add the time elapsed in microseconds
            //NOTE, I SOMETIMES GOT TOTALLY BOGUS TIMES WHEN TESTING WITH DMD
            //WHEN YOU TEST WITH LDC, YOU SHOULD GET ACCURATE TIMING INFO...
            }
            long averageTime = totalTime/numRepsToAvg;
            file.writeln("k,quad,"~(to!string(k)) ~",1000,2,G,"~(to!string(averageTime)));
            writeln("The average time for ", k, "number of neighbors is: ", averageTime);
        }
    }

    //varying N values
    writeln("varying N values");

    foreach(n; nVals){
        writeln("n = ", n);
        for(int reps = 0; reps < numTimingReps; reps++){
            //will want to repeat and average
            long totalTime = 0;
            auto trainingPoints = getGaussianPoints!2(n);
            auto testingPoints = getUniformPoints!2(n/100);
            auto qt = QuadTree(trainingPoints);
            auto sw = StopWatch(AutoStart.no);
            for(int round = 0; round < numRepsToAvg; round++){
                sw.start; //start my stopwatch
                foreach(const ref qp; testingPoints){
                    qt.KNNQuery(qp, defaultK);
                }
                sw.stop;
                totalTime+= sw.peek.total!"usecs";  //add the time elapsed in microseconds
            //NOTE, I SOMETIMES GOT TOTALLY BOGUS TIMES WHEN TESTING WITH DMD
            //WHEN YOU TEST WITH LDC, YOU SHOULD GET ACCURATE TIMING INFO...
            }
            long averageTime = totalTime/numRepsToAvg;
            file.writeln("n,quad,25,"~(to!string(n))~",2,G,"~(to!string(averageTime)));
            writeln("The average time for a training size of ", n, " is: ", averageTime);
        }
    }

    //varying K + N together!
    writeln("varying both K + N");
    for(int index = 0; index < kVals.length; index++){
        int k = kVals[index];
        int n = nVals[index];
        writeln("k = ", k, " n = ", n);
        for(int reps = 0; reps < numTimingReps; reps++){
        //will want to repeat and average
            long totalTime = 0;     
            auto trainingPoints = getGaussianPoints!2(n);
            auto testingPoints = getUniformPoints!2(n/100);
            auto qt = QuadTree(trainingPoints);
            auto sw = StopWatch(AutoStart.no);
            for(int round = 0; round < numRepsToAvg; round++){
                sw.start; //start my stopwatch
                foreach(const ref qp; testingPoints){
                    qt.KNNQuery(qp, k);
                }
                sw.stop;
                totalTime+= sw.peek.total!"usecs";  //add the time elapsed in microseconds
            //NOTE, I SOMETIMES GOT TOTALLY BOGUS TIMES WHEN TESTING WITH DMD
            //WHEN YOU TEST WITH LDC, YOU SHOULD GET ACCURATE TIMING INFO...
            }
            long averageTime = totalTime/numRepsToAvg;
            file.writeln("kn,quad,"~(to!string(k))~","~(to!string(n))~",2,G,"~(to!string(averageTime)));
            writeln("The average time for ", k, " number of neighbors and ", n, " size of training set is: ", averageTime);
        }
    }

    writeln("Uniform  distribution");
    //varying K values
    writeln("varying K values");
    foreach(k; kVals){
        writeln("k = ", k);
        for(int reps = 0; reps < numTimingReps; reps++){
        //will want to repeat and average
            long totalTime = 0;     
            auto trainingPoints = getUniformPoints!2(1000);
            auto testingPoints = getUniformPoints!2(100);
            auto qt = QuadTree(trainingPoints);
            auto sw = StopWatch(AutoStart.no);
            for(int round = 0; round < numRepsToAvg; round++){
                sw.start; //start my stopwatch
                foreach(const ref qp; testingPoints){
                    qt.KNNQuery(qp, k);
                }
                sw.stop;
                totalTime+= sw.peek.total!"usecs";  //add the time elapsed in microseconds
            //NOTE, I SOMETIMES GOT TOTALLY BOGUS TIMES WHEN TESTING WITH DMD
            //WHEN YOU TEST WITH LDC, YOU SHOULD GET ACCURATE TIMING INFO...
            }
            long averageTime = totalTime/numRepsToAvg;
            file.writeln("k,quad,"~(to!string(k)) ~",1000,2,U,"~(to!string(averageTime)));
            writeln("The average time for ", k, "number of neighbors is: ", averageTime);
        }
    }

    //varying N values
    writeln("varying N values");

    foreach(n; nVals){
        writeln("n = ", n);
        for(int reps = 0; reps < numTimingReps; reps++){
            //will want to repeat and average
            long totalTime = 0;
            auto trainingPoints = getUniformPoints!2(n);
            auto testingPoints = getUniformPoints!2(n/100);
            auto qt = QuadTree(trainingPoints);
            auto sw = StopWatch(AutoStart.no);
            for(int round = 0; round < numRepsToAvg; round++){
                sw.start; //start my stopwatch
                foreach(const ref qp; testingPoints){
                    qt.KNNQuery(qp, defaultK);
                }
                sw.stop;
                totalTime+= sw.peek.total!"usecs";  //add the time elapsed in microseconds
            //NOTE, I SOMETIMES GOT TOTALLY BOGUS TIMES WHEN TESTING WITH DMD
            //WHEN YOU TEST WITH LDC, YOU SHOULD GET ACCURATE TIMING INFO...
            }
            long averageTime = totalTime/numRepsToAvg;
            file.writeln("n,quad,25,"~(to!string(n))~",2,U,"~(to!string(averageTime)));
            writeln("The average time for a training size of ", n, " is: ", averageTime);
        }
    }

    //varying K + N together!
    writeln("varying both K + N");
    for(int index = 0; index < kVals.length; index++){
        int k = kVals[index];
        int n = nVals[index];
        writeln("k = ", k, " n = ", n);
        for(int reps = 0; reps < numTimingReps; reps++){
        //will want to repeat and average
            long totalTime = 0;     
            auto trainingPoints = getUniformPoints!2(n);
            auto testingPoints = getUniformPoints!2(n/100);
            auto qt = QuadTree(trainingPoints);
            auto sw = StopWatch(AutoStart.no);
            for(int round = 0; round < numRepsToAvg; round++){
                sw.start; //start my stopwatch
                foreach(const ref qp; testingPoints){
                    qt.KNNQuery(qp, k);
                }
                sw.stop;
                totalTime+= sw.peek.total!"usecs";  //add the time elapsed in microseconds
            //NOTE, I SOMETIMES GOT TOTALLY BOGUS TIMES WHEN TESTING WITH DMD
            //WHEN YOU TEST WITH LDC, YOU SHOULD GET ACCURATE TIMING INFO...
            }
            long averageTime = totalTime/numRepsToAvg;
            file.writeln("kn,quad,"~(to!string(k))~","~(to!string(n))~",2,U,"~(to!string(averageTime)));
            writeln("The average time for ", k, " number of neighbors and ", n, " size of training set is: ", averageTime);
        }
    }


    //KDTREE
    writeln("KDTree");
    writeln("Gaussian distribution");

    //varying K values
    writeln("varying K values");
    foreach(k; kVals){
        writeln("k = ", k);
        for(int reps = 0; reps < numTimingReps; reps++){
        //will want to repeat and average
            long totalTime = 0;     
            auto trainingPoints = getGaussianPoints!2(1000);
            auto testingPoints = getUniformPoints!2(100);
            auto kt = KDTree!2(trainingPoints);
            auto sw = StopWatch(AutoStart.no);
            for(int round = 0; round < numRepsToAvg; round++){
                sw.start; //start my stopwatch
                foreach(const ref qp; testingPoints){
                    kt.KNNQuery(qp, k);
                }
                sw.stop;
                totalTime+= sw.peek.total!"usecs";  //add the time elapsed in microseconds
            //NOTE, I SOMETIMES GOT TOTALLY BOGUS TIMES WHEN TESTING WITH DMD
            //WHEN YOU TEST WITH LDC, YOU SHOULD GET ACCURATE TIMING INFO...
            }
            long averageTime = totalTime/numRepsToAvg;
            file.writeln("k,kd,"~(to!string(k)) ~",1000,2,G,"~(to!string(averageTime)));
            writeln("The average time for ", k, "number of neighbors is: ", averageTime);
        }
    }

    //varying N values
    writeln("varying N values");

    foreach(n; nVals){
        writeln("n = ", n);
        for(int reps = 0; reps < numTimingReps; reps++){
            //will want to repeat and average
            long totalTime = 0;
            auto trainingPoints = getGaussianPoints!2(n);
            auto testingPoints = getUniformPoints!2(n/100);
            auto kt = KDTree!2(trainingPoints);
            auto sw = StopWatch(AutoStart.no);
            for(int round = 0; round < numRepsToAvg; round++){
                sw.start; //start my stopwatch
                foreach(const ref qp; testingPoints){
                    kt.KNNQuery(qp, defaultK);
                }
                sw.stop;
                totalTime+= sw.peek.total!"usecs";  //add the time elapsed in microseconds
            //NOTE, I SOMETIMES GOT TOTALLY BOGUS TIMES WHEN TESTING WITH DMD
            //WHEN YOU TEST WITH LDC, YOU SHOULD GET ACCURATE TIMING INFO...
            }
            long averageTime = totalTime/numRepsToAvg;
            file.writeln("n,kd,25,"~(to!string(n))~",2,G,"~(to!string(averageTime)));
            writeln("The average time for a training size of ", n, " is: ", averageTime);
        }
    }

    //varying K + N together!
    writeln("varying both K + N");
    for(int index = 0; index < kVals.length; index++){
        int k = kVals[index];
        int n = nVals[index];
        writeln("k = ", k, " n = ", n);
        for(int reps = 0; reps < numTimingReps; reps++){
        //will want to repeat and average
            long totalTime = 0;     
            auto trainingPoints = getGaussianPoints!2(n);
            auto testingPoints = getUniformPoints!2(n/100);
            auto kt = KDTree!2(trainingPoints);
            auto sw = StopWatch(AutoStart.no);
            for(int round = 0; round < numRepsToAvg; round++){
                sw.start; //start my stopwatch
                foreach(const ref qp; testingPoints){
                    kt.KNNQuery(qp, k);
                }
                sw.stop;
                totalTime+= sw.peek.total!"usecs";  //add the time elapsed in microseconds
            //NOTE, I SOMETIMES GOT TOTALLY BOGUS TIMES WHEN TESTING WITH DMD
            //WHEN YOU TEST WITH LDC, YOU SHOULD GET ACCURATE TIMING INFO...
            }
            long averageTime = totalTime/numRepsToAvg;
            file.writeln("kn,kd,"~(to!string(k))~","~(to!string(n))~",2,G,"~(to!string(averageTime)));
            writeln("The average time for ", k, " number of neighbors and ", n, " size of training set is: ", averageTime);
        }
    }

    //varying D values
    writeln("varying D values");
    //Same tests for the BucketKNN
    static foreach(dim; 1..8){{
        for(int reps = 0; reps < numTimingReps; reps++){
            long totalTime = 0;
            //get points of the appropriate dimension
            enum numTrainingPoints = 1000;
            auto trainingPoints = getUniformPoints!dim(numTrainingPoints);
            auto testingPoints = getUniformPoints!dim(100);
            auto kd = KDTree!dim(trainingPoints); //rough estimate to get 64 points per cell on average
            writeln("tree of dimension ", dim, " built");
            auto sw = StopWatch(AutoStart.no);
            for(int round = 0; round < numRepsToAvg; round++){
                sw.start; //start my stopwatch
                foreach(const ref qp; testingPoints){
                    kd.KNNQuery(qp, 10);
                }
                sw.stop;
                totalTime+= sw.peek.total!"usecs";
            }
            long averageTime = totalTime/numRepsToAvg;
            file.writeln("d,kd,25,1000,"~(to!string(dim))~",G,"~(to!string(averageTime)));
            writeln("The average time for a dimension of ", dim, " is: ", averageTime);
    
        }
    }}

    writeln("Uniform distribution");

    //varying K values
    writeln("varying K values");
    foreach(k; kVals){
        writeln("k = ", k);
        for(int reps = 0; reps < numTimingReps; reps++){
        //will want to repeat and average
            long totalTime = 0;     
            auto trainingPoints = getUniformPoints!2(1000);
            auto testingPoints = getUniformPoints!2(100);
            auto kt = KDTree!2(trainingPoints);
            auto sw = StopWatch(AutoStart.no);
            for(int round = 0; round < numRepsToAvg; round++){
                sw.start; //start my stopwatch
                foreach(const ref qp; testingPoints){
                    kt.KNNQuery(qp, k);
                }
                sw.stop;
                totalTime+= sw.peek.total!"usecs";  //add the time elapsed in microseconds
            //NOTE, I SOMETIMES GOT TOTALLY BOGUS TIMES WHEN TESTING WITH DMD
            //WHEN YOU TEST WITH LDC, YOU SHOULD GET ACCURATE TIMING INFO...
            }
            long averageTime = totalTime/numRepsToAvg;
            file.writeln("k,kd,"~(to!string(k)) ~",1000,2,U,"~(to!string(averageTime)));
            writeln("The average time for ", k, "number of neighbors is: ", averageTime);
        }
    }

    //varying N values
    writeln("varying N values");

    foreach(n; nVals){
        writeln("n = ", n);
        for(int reps = 0; reps < numTimingReps; reps++){
            //will want to repeat and average
            long totalTime = 0;
            auto trainingPoints = getUniformPoints!2(n);
            auto testingPoints = getUniformPoints!2(n/100);
            auto kt = KDTree!2(trainingPoints);
            auto sw = StopWatch(AutoStart.no);
            for(int round = 0; round < numRepsToAvg; round++){
                sw.start; //start my stopwatch
                foreach(const ref qp; testingPoints){
                    kt.KNNQuery(qp, defaultK);
                }
                sw.stop;
                totalTime+= sw.peek.total!"usecs";  //add the time elapsed in microseconds
            //NOTE, I SOMETIMES GOT TOTALLY BOGUS TIMES WHEN TESTING WITH DMD
            //WHEN YOU TEST WITH LDC, YOU SHOULD GET ACCURATE TIMING INFO...
            }
            long averageTime = totalTime/numRepsToAvg;
            file.writeln("n,kd,25,"~(to!string(n))~",2,U,"~(to!string(averageTime)));
            writeln("The average time for a training size of ", n, " is: ", averageTime);
        }
    }

    //varying K + N together!
    writeln("varying both K + N");
    for(int index = 0; index < kVals.length; index++){
        int k = kVals[index];
        int n = nVals[index];
        writeln("k = ", k, " n = ", n);
        for(int reps = 0; reps < numTimingReps; reps++){
        //will want to repeat and average
            long totalTime = 0;     
            auto trainingPoints = getUniformPoints!2(n);
            auto testingPoints = getUniformPoints!2(n/100);
            auto kt = KDTree!2(trainingPoints);
            auto sw = StopWatch(AutoStart.no);
            for(int round = 0; round < numRepsToAvg; round++){
                sw.start; //start my stopwatch
                foreach(const ref qp; testingPoints){
                    kt.KNNQuery(qp, k);
                }
                sw.stop;
                totalTime+= sw.peek.total!"usecs";  //add the time elapsed in microseconds
            //NOTE, I SOMETIMES GOT TOTALLY BOGUS TIMES WHEN TESTING WITH DMD
            //WHEN YOU TEST WITH LDC, YOU SHOULD GET ACCURATE TIMING INFO...
            }
            long averageTime = totalTime/numRepsToAvg;
            file.writeln("kn,kd,"~(to!string(k))~","~(to!string(n))~",2,U,"~(to!string(averageTime)));
            writeln("The average time for ", k, " number of neighbors and ", n, " size of training set is: ", averageTime);
        }
    }

    //varying D values
    writeln("varying D values");
    static foreach(dim; 1..8){{
        for(int reps = 0; reps < numTimingReps; reps++){
            long totalTime = 0;
            //get points of the appropriate dimension
            enum numTrainingPoints = 1000;
            auto trainingPoints = getUniformPoints!dim(numTrainingPoints);
            auto testingPoints = getUniformPoints!dim(100);
            auto kd = KDTree!dim(trainingPoints); //rough estimate to get 64 points per cell on average
            writeln("tree of dimension ", dim, " built");
            auto sw = StopWatch(AutoStart.no);
            for(int round = 0; round < numRepsToAvg; round++){
                sw.start; //start my stopwatch
                foreach(const ref qp; testingPoints){
                    kd.KNNQuery(qp, 10);
                }
                sw.stop;
                totalTime+= sw.peek.total!"usecs";
            }
            long averageTime = totalTime/numRepsToAvg;
            file.writeln("d,kd,25,1000,"~(to!string(dim))~",U,"~(to!string(averageTime)));
            writeln("The average time for a dimension of ", dim, " is: ", averageTime);
    
        }
    }}

    file.close();



    //because dim is a "compile time parameter" we have to use "static foreach"
    //to loop through all the dimensions we want to test.
    //the {{ are necessary because this block basically gets copy/pasted with
    //dim filled in with 1, 2, 3, ... 7.  The second set of { lets us reuse
    //variable names.

    // writeln("dumbKNN results");
    // static foreach(dim; 1..8){{
    //     //get points of the appropriate dimension
    //     auto trainingPoints = getGaussianPoints!dim(1000);
    //     auto testingPoints = getUniformPoints!dim(100);
    //     auto kd = DumbKNN!dim(trainingPoints);
    //     writeln("tree of dimension ", dim, " built");
    //     auto sw = StopWatch(AutoStart.no);
    //     sw.start; //start my stopwatch
    //     foreach(const ref qp; testingPoints){
    //         kd.knnQuery(qp, 10);
    //     }
    //     sw.stop;
    //     writeln(dim, sw.peek.total!"usecs"); //output the time elapsed in microseconds
    //     //NOTE, I SOMETIMES GOT TOTALLY BOGUS TIMES WHEN TESTING WITH DMD
    //     //WHEN YOU TEST WITH LDC, YOU SHOULD GET ACCURATE TIMING INFO...
    // }}











    // writeln("");
    // writeln("QuadTree results: ");
    //Same tests for QuadTree
    



    // writeln("KNNquery: ");
    // auto knnQueryRes = qt.KNNQuery(Point!2([0, 0]), 3);
    // writeln("The results are: ", knnQueryRes);



    //KDTre
    



                //, Point!2([5, 7.5]) ,Point!2([7.5, 7.5]), Point!2([10, 7.5])


    // auto pointsKD = [Point!2([5, 5]) ,Point!2([7.5, 5]), Point!2([10, 5]),
    //             Point!2([0,7.5]), Point!2([2.5,7.5]), Point!2([5, 7.5]) ,Point!2([7.5, 7.5]), Point!2([10, 7.5]),
    //             Point!2([0, 10]), Point!2([2.5, 10]), Point!2([5, 10]) ,Point!2([7.5, 10]), Point!2([10, 10]), Point!2([0,0]), Point!2([2.5,0]), Point!2([5,0]), Point!2([7.5,0]), Point!2([10,0]),
    //             Point!2([0,2.5]), Point!2([2.5,2.5]), Point!2([5, 2.5]) ,Point!2([7.5,2.5]), Point!2([10, 2.5]),
    //             Point!2([0,5]), Point!2([2.5,5])];

        // auto pointsKD = [Point!3([0,0, 0]), Point!3([2.5,0, 0]), Point!3([5,0, 0]), Point!3([7.5,0, 0]), Point!3([10,0, 0]),
        //         Point!3([0,2.5, 2.5]), Point!3([2.5,2.5, 2.5]), Point!3([5, 2.5, 2.5]) ,Point!3([7.5,2.5, 2.5]), Point!3([10, 2.5, 2.5]),
        //         Point!3([0,5, 5]), Point!3([2.5,5, 5]), Point!3([5, 5, 5]) ,Point!3([7.5, 5, 5]), Point!3([10, 5, 5]),
        //         Point!3([0,7.5, 7.5]), Point!3([2.5,7.5, 7.5]), Point!3([5, 7.5, 7.5]) ,Point!3([7.5, 7.5, 7.5]), Point!3([10, 7.5, 7.5]),
        //         Point!3([0, 10, 10]), Point!3([2.5, 10, 10]), Point!3([5, 10, 10]) ,Point!3([7.5, 10, 10]), Point!3([10, 10, 10])];

    // writeln("KDTree: ");
    


    // auto kt = KDTree!2(pointsKD);
    // auto kt = KDTree!3(pointsKD);




}
