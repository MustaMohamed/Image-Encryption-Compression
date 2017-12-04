package sample.Helpers.Compression;

import sample.Helpers.HuffmanTree.HuffmanNode;
import sample.Helpers.HuffmanTree.HuffmanTree;
import sample.Helpers.ImageContainers.Pixel;
import sample.Helpers.ImageControllers.ImageLoader;

public class CompressionController {
  
  private HuffmanTree mRedTree;
  private HuffmanTree mGreenTree;
  private HuffmanTree mBlueTree;
  private long[][] mFrequencies;
  
  public CompressionController(Pixel[][] sourceMatrix) {
    
    mFrequencies = new long[3][260];
    mRedTree = new HuffmanTree();
    mGreenTree = new HuffmanTree();
    mBlueTree = new HuffmanTree();
    
    initFrequency(sourceMatrix);
    initHuffmanNodes();
    buildTees();
  }
  
  private void initFrequency(Pixel[][] sourceMatrix) {
    // get height and width of the image
    int matrixWidth = ImageLoader.getImageWidth(sourceMatrix),
      matrixHeight = ImageLoader.getImageHeight(sourceMatrix);
    for (int i = 0; i < matrixHeight; i++) {
      for (int j = 0; j < matrixWidth; j++) {
      
        int redValue = sourceMatrix[i][j].getRed();
        int greenValue = sourceMatrix[i][j].getGreen();
        int blueValue = sourceMatrix[i][j].getBlue();
        
        mFrequencies[0][redValue]++;
        mFrequencies[1][greenValue]++;
        mFrequencies[2][blueValue]++;
      }
    }
  }
  
  private void initHuffmanNodes() {
    for (int i = 0; i < 256; i++) {
      for(int j = 0; j < 3; j++) {
        if(mFrequencies[j][i] > 0) {
          if (j == 0) mRedTree.addNode(new HuffmanNode(i, mFrequencies[j][i]));
          else if (j == 1) mGreenTree.addNode(new HuffmanNode(i, mFrequencies[j][i]));
          else if (j == 2) mBlueTree.addNode(new HuffmanNode(i, mFrequencies[j][i]));
        }
      }
    }
  }
  
  private void buildTees() {
    mRedTree.buildTree();
    mGreenTree.buildTree();
    mBlueTree.buildTree();
    System.out.println(mRedTree.toString());
    System.out.println(mGreenTree.toString());
    System.out.println(mBlueTree.toString());
  }
}
