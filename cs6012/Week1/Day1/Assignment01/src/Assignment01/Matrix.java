package Assignment01;

public class Matrix {
  int numRows;
  int numColumns;
  int[][] data;

  // Constructor with data for new matrix (automatically determines dimensions)
  public Matrix(int[][] data) {
    numRows = data.length; // d.length is the number of 1D arrays in the 2D array
    if (numRows == 0) {
      numColumns = 0;
    } else {
      numColumns = data[0].length; // d[0] is the first 1D array
    }
    this.data = new int[numRows][numColumns]; // create a new matrix to hold the data
    // copy the data over
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numColumns; j++) {
        this.data[i][j] = data[i][j];
      }
    }
  }

  @Override // instruct the compiler that we do indeed intend for this method to override
            // the superclass' (Object) version
  public boolean equals(Object other) {
    if (!(other instanceof Matrix)) { // make sure the Object we're comparing to is a Matrix
      return false;
    }
    Matrix matrix = (Matrix) other; // if the above was not true, we know it's safe to treat 'o' as a Matrix
    if (matrix.numRows != this.numRows || matrix.numColumns != this.numColumns){
      return false;
    }else{
      for (int i = 0; i < this.numRows; i++){
        for (int j = 0; j < this.numColumns; j++){
          if (matrix.data[i][j] != this.data[i][j]){
            return false;
          }
        }
      }
      return true;
    }
  }

  @Override // instruct the compiler that we do indeed intend for this method to override
            // the superclass' (Object) version
  public String toString() {
    StringBuilder matrixString = new StringBuilder();
    for (int i = 0; i < this.numRows; i++){
      for (int j = 0; j < this.numColumns; j++){
        matrixString.append(this.data[i][j]).append(" ");
      }
      matrixString.append("\n");
    }
    return matrixString.toString(); // placeholder
  }

  public Matrix times(Matrix matrix) {
    if (this.numColumns != matrix.numRows){
      return null;
    }else{
      int[][] timesMatrixData = new int[this.numRows][matrix.numColumns];
      for (int i = 0; i < this.numRows; i++){
        for (int j = 0; j < matrix.numColumns; j++){
          for (int k = 0; k < matrix.numRows; k++){
            timesMatrixData[i][j] += this.data[i][k] * matrix.data[k][j];
          }
        }
      }
      return (new Matrix(timesMatrixData));

    }
    /*
     * TODO: replace the below return statement with the correct code, This function
     * must check if the two matrices are compatible for multiplication, if not,
     * return null. If they are compatible, determine the dimensions of the
     * resulting matrix, and fill it in with the correct values for matrix
     * multiplication
     */
  }

  public Matrix plus(Matrix matrix) {
    if (matrix.numRows != this.numRows || matrix.numColumns != this.numColumns){
      return null;
    }else {
      int[][] addedMatrixData = new int[this.numRows][this.numColumns];
      for (int a = 0; a < this.numRows; a++){
        for (int j = 0; j < this.numColumns; j++){
          addedMatrixData[a][j] = matrix.data[a][j] + this.data[a][j];
        }
      }
      return new Matrix(addedMatrixData);
    }
  }
}
