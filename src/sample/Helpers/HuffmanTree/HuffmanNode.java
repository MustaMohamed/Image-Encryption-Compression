package sample.Helpers.HuffmanTree;

import org.jetbrains.annotations.NotNull;

/**
 * Instance Class te represent Huffman Tree Node
 *
 * @author Musta Mohamed
 */
public class HuffmanNode implements Comparable<HuffmanNode> {
  
  // the node value character or integer
  private int mNodeValue;
  // the number of occurs in the file
  private long mFrequency;
  // the right child node
  private HuffmanNode mRightNode;
  // the left child node
  private HuffmanNode mLeftNode;
  
  /**
   * Object Constructor
   *
   * @param nodeValue int           the value of the node
   * @param frequency long          the number of occurs in file
   * @param rightNode HuffmanNode   the right child node in the tree
   * @param leftNode  HuffmanNode   the left child node in the tree
   */
  public HuffmanNode(int nodeValue, long frequency, HuffmanNode rightNode, HuffmanNode leftNode) {
    this.mNodeValue = nodeValue;
    this.mFrequency = frequency;
    this.mRightNode = rightNode;
    this.mLeftNode = leftNode;
  }
  
  /**
   * Object Overloaded Constructor
   *
   * @param mNodeValue int           the value of the node
   * @param mFrequency long          the number of occurs in file
   */
  public HuffmanNode(int mNodeValue, long mFrequency) {
    this.mNodeValue = mNodeValue;
    this.mFrequency = mFrequency;
    this.mRightNode = null;
    this.mLeftNode = null;
  }
  
  
  /**
   * Object Overloaded Empty Constructor
   */
  public HuffmanNode() {
    this.mRightNode = null;
    this.mLeftNode = null;
  }
  
  public HuffmanNode getRightNode() {
    return mRightNode;
  }
  
  public void setRightNode(HuffmanNode rightNode) {
    this.mRightNode = rightNode;
  }
  
  public HuffmanNode getLeftNode() {
    return mLeftNode;
  }
  
  public void setLeftNode(HuffmanNode leftNode) {
    this.mLeftNode = leftNode;
  }
  
  public int getNodeValue() {
    return mNodeValue;
  }
  
  public long getFrequency() {
    return mFrequency;
  }
  
  /**
   * Check if the node is leaf or not that means the node is the end of th path in the tree.
   *
   * @return boolean   true if the node if leaf, false otherwise.
   */
  public boolean isLeaf() {
    return this.mRightNode == null && this.mLeftNode == null;
  }
  
  
  /**
   * Object comparator compare to nodes
   *
   * @param o HuffmanNode   the other node to compare with
   *
   * @return int           0 if the 2 node are equal positive if the first is greater negative otherwise
   */
  @Override
  public int compareTo(@NotNull HuffmanNode o) {
    int compValue = 0;
    if (this.mFrequency > o.getFrequency()) compValue = 1;
    if (this.mFrequency < o.getFrequency()) compValue = -1;
    return compValue;
  }
  
  /**
   * Object equal checker compare to nodes
   *
   * @param o HuffmanNode   the other node to compare with
   *
   * @return int           true if the 2 node are equal false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    
    HuffmanNode that = (HuffmanNode) o;
    
    if (mNodeValue != that.mNodeValue) return false;
    if (mFrequency != that.mFrequency) return false;
    if (mRightNode != null ? !mRightNode.equals(that.mRightNode) : that.mRightNode != null) return false;
    return mLeftNode != null ? mLeftNode.equals(that.mLeftNode) : that.mLeftNode == null;
  }
  
  
  @Override
  public String toString() {
    String str = "";
    str += mNodeValue + " => " + mFrequency;
    return str;
  }
}
