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
    if (!(other instanceof Matrix matrix)) { // make sure the Object we're comparing to is a Matrix
      return false;
    }
    // if the above was not true, we know it's safe to treat 'o' as a Matrix
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

  //times multiplies 2 matrices by each other if their rows and columns are compatible
  public Matrix times(Matrix matrix) {
    //checks compatibility
    if (this.numColumns != matrix.numRows){
      return null;
    }else{
      int[][] timesMatrixData = new int[this.numRows][matrix.numColumns];
      //for loop for this matrix's rows
      for (int i = 0; i < this.numRows; i++){
        //for loop for the other matrix's columns
        for (int j = 0; j < matrix.numColumns; j++){
          //for loop to multiply the rows by the columns and store it in the new data array
          for (int k = 0; k < matrix.numRows; k++){
            timesMatrixData[i][j] += this.data[i][k] * matrix.data[k][j];
          }
        }
      }
      return (new Matrix(timesMatrixData));

    }
  }

  //plus adds to matrices together if they are the same number of columns and rows
  public Matrix plus(Matrix matrix) {
    //This checks if the 2 matrices are compatible for addition
    if (matrix.numRows != this.numRows || matrix.numColumns != this.numColumns){
      return null;
    }else {
      //new matrix with the same rows and columns as both of our matrices we are adding
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
