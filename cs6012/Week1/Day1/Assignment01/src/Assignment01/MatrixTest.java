package Assignment01;

import org.junit.jupiter.api.Test;

class MatrixTest {
    Matrix matrix1;
    Matrix matrix1Copy;
    Matrix tinyMatrix;
    Matrix matrix2;
    Matrix added1And2AnswerMatrix;
    Matrix threeByTwoMatrix;
    Matrix twoByThreeMatrix;
    Matrix twoByTwoZeroMatrix;
    Matrix threeByTwoZeroMatrix;


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        int[][] matrix1Data = new int[2][2];
        matrix1Data[0][0] = 2;
        matrix1Data[0][1] = 1;
        matrix1Data[1][0] = 3;
        matrix1Data[1][1] = 4;
        matrix1 = new Matrix(matrix1Data);

        int[][] matrix1CopyData = new int[2][2];
        matrix1CopyData[0][0] = 2;
        matrix1CopyData[0][1] = 1;
        matrix1CopyData[1][0] = 3;
        matrix1CopyData[1][1] = 4;
        matrix1Copy = new Matrix(matrix1CopyData);

        int[][] tinyMatrixData = new int[1][1];
        tinyMatrixData[0][0] = 25;
        tinyMatrix = new Matrix(tinyMatrixData);

        int[][] matrix2Data = new int[2][2];
        matrix2Data[0][0] = 2;
        matrix2Data[0][1] = 1;
        matrix2Data[1][0] = 3;
        matrix2Data[1][1] = 16;
        matrix2 = new Matrix(matrix2Data);

        int[][] matrixAddedData = new int[2][2];
        matrixAddedData[0][0] = 4;
        matrixAddedData[0][1] = 2;
        matrixAddedData[1][0] = 6;
        matrixAddedData[1][1] = 20;
        added1And2AnswerMatrix = new Matrix(matrixAddedData);

        int[][] threeByTwoData = new int[2][3];
        threeByTwoData[0][0] = 1;
        threeByTwoData[1][0] = 4;
        threeByTwoData[0][1] = 2;
        threeByTwoData[1][1] = 5;
        threeByTwoData[0][2] = 3;
        threeByTwoData[1][2] = 6;
        threeByTwoMatrix = new Matrix(threeByTwoData);

        int[][] twoByThreeData = new int[3][2];
        twoByThreeData[0][0] = 7;
        twoByThreeData[0][1] = 8;
        twoByThreeData[1][0] = 9;
        twoByThreeData[1][1] = 10;
        twoByThreeData[2][0] = 11;
        twoByThreeData[2][1] = 12;
        twoByThreeMatrix = new Matrix(twoByThreeData);

        int[][] twoByTwoZeroData = new int[2][2];
        twoByTwoZeroData[0][0] = 0;
        twoByTwoZeroData[0][1] = 0;
        twoByTwoZeroData[1][0] = 0;
        twoByTwoZeroData[1][1] = 0;
        twoByTwoZeroMatrix = new Matrix(twoByTwoZeroData);

        int[][] threeByTwoZeroData = new int[2][3];
        threeByTwoZeroData[0][0] = 0;
        threeByTwoZeroData[1][0] = 0;
        threeByTwoZeroData[0][1] = 0;
        threeByTwoZeroData[1][1] = 0;
        threeByTwoZeroData[0][2] = 0;
        threeByTwoZeroData[1][2] = 0;
        threeByTwoZeroMatrix = new Matrix(threeByTwoZeroData);

    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        matrix1 = null;
        matrix1Copy = null;
        tinyMatrix = null;
        matrix2 = null;
        added1And2AnswerMatrix = null;
        threeByTwoMatrix = null;
        twoByThreeMatrix = null;
        twoByTwoZeroMatrix = null;
        threeByTwoZeroMatrix = null;
    }

    //Basic assert equals to verify that 2 matrices with the same dimensions and contents will return true
    @org.junit.jupiter.api.Test
    void testEqualsTwoByTwoMatrix() {
        assert(matrix1.equals(matrix1Copy));
    }

    //This checks the equality method to verify it accurately returns false on Matrices of different sizes
    @Test
    void testEqualOnDifferentSizes(){
        assert(!matrix1.equals(tinyMatrix));
    }
    //This test verifies that 2 matrices with the same dimensions but different values do not equal each other
    @Test
    void testEqualsTwoByTwoDimensionsWithSingleDifferentValue(){
        assert(!matrix1.equals(matrix2));
    }


    //This test checks for a standard 2x2 matrices being converted into a string
    @org.junit.jupiter.api.Test
    void testToString() {
        assert(matrix1.toString().equals("2 1 \n3 4 \n"));
    }
    //This test verifies the toString works on a small array with a multi digit int
    @Test
    void testTinyMatrixToString() {
        assert(tinyMatrix.toString().equals("25 \n"));
    }

    //This tests the times on a matrix with the same dimensions
    @org.junit.jupiter.api.Test
    void testTimesTwoByTwoMatrices() {
        String correctAnswer = "7 18 \n18 67 \n";
        assert(matrix1.times(matrix2).toString().equals(correctAnswer));
    }
    //This will test multiplication with a 2x2 matrix with a 3x2 matrix of zeros
    @Test
    void testTimesThreeByTwoAndTwoByTwoOfZeros(){
        assert (twoByTwoZeroMatrix.times(threeByTwoMatrix).equals(threeByTwoZeroMatrix));
    }

    //This makes sure that the general multiplication method works when the # rows in the rights matrix is the
    // same as the columns on the left matrix
    @Test
    void testThreeByTwoTimesTwoByThree(){
        String correctAnswer = "58 64 \n139 154 \n";
        assert(threeByTwoMatrix.times(twoByThreeMatrix).toString().equals(correctAnswer));
    }
    //This will test that trying to multiply 2 matrices of incorrect size will return null
    @Test
    void testThreeByTwoTimesThreeByTwo(){
        assert (threeByTwoMatrix.times(threeByTwoMatrix) == null);
    }

   //This tests addition on 2 standard 2x2 matrices of the same size
    @org.junit.jupiter.api.Test
    void testPlusWithTwoByTwoMatrices() {
        assert(matrix1.plus(matrix2).equals(added1And2AnswerMatrix));
    }

    //This test verifies that trying to add 2 matrices together with different sizes will return null
    @Test
    void testPlusWithDifferentSizedMatrices(){
        assert (matrix1.plus(tinyMatrix) == null);
    }
    //This test checks if plus works with matrices filled with all 0s
    @Test
    void testPlusWithATwoByTwoMatricesOfZeros(){
        assert (twoByTwoZeroMatrix.plus(matrix2).equals(matrix2));
    }
    //This test checks if plus works on three by two sized matrices of 0s
    @Test
    void testPlusWithThreeByTwoMatricesOfZeros(){
        assert (threeByTwoMatrix.plus(threeByTwoZeroMatrix).equals(threeByTwoMatrix));
    }
}
