package sample.Helpers.HuffmanTree;

import org.jetbrains.annotations.NotNull;

/**
 * Instance Class for Huffman Tree Node
 */
public class HuffmanNode implements Comparable<HuffmanNode> {
  
  private char mNodeValue;
  private int mFrequency;
  private HuffmanNode mRightNode;
  private HuffmanNode mLeftNode;
  
  public HuffmanNode(char nodeValue, int frequency, HuffmanNode rightNode, HuffmanNode leftNode) {
    this.mNodeValue = nodeValue;
    this.mFrequency = frequency;
    this.mRightNode = rightNode;
    this.mLeftNode = leftNode;
  }
  
  public HuffmanNode(char mNodeValue, int mFrequency) {
    this.mNodeValue = mNodeValue;
    this.mFrequency = mFrequency;
    this.mRightNode = null;
    this.mLeftNode = null;
  }
  
  public HuffmanNode() {
    this.mRightNode = null;
    this.mLeftNode = null;
  }
  
  public boolean isLeaf() {
    return this.mRightNode == null || this.mLeftNode == null;
  }
  
  public char getNodeValue() {
    return mNodeValue;
  }
  
  public int getFrequency() {
    return mFrequency;
  }
  
  public HuffmanNode getRightNode() {
    return mRightNode;
  }
  
  public HuffmanNode getLeftNode() {
    return mLeftNode;
  }
  
  @Override
  public int compareTo(@NotNull HuffmanNode o) {
    return this.mFrequency - o.getFrequency();
  }
  
  @Override
  public String toString() {
    String str = "";
    str += mNodeValue + " => " + mFrequency;
    return str;
  }
}
