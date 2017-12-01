package sample.Helpers.HuffmanTree;

import org.jetbrains.annotations.NotNull;

/**
 * Instance Class for Huffman Tree Node
 */
public class HuffmanNode implements Comparable<HuffmanNode> {
  
  private int mNodeValue;
  private long mFrequency;
  private HuffmanNode mRightNode;
  private HuffmanNode mLeftNode;
  
  public HuffmanNode(int nodeValue, long frequency, HuffmanNode rightNode, HuffmanNode leftNode) {
    this.mNodeValue = nodeValue;
    this.mFrequency = frequency;
    this.mRightNode = rightNode;
    this.mLeftNode = leftNode;
  }
  
  public HuffmanNode(int mNodeValue, long mFrequency) {
    this.mNodeValue = mNodeValue;
    this.mFrequency = mFrequency;
    this.mRightNode = null;
    this.mLeftNode = null;
  }
  
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
  
  public boolean isLeaf() {
    return this.mRightNode == null && this.mLeftNode == null;
  }
  
  @Override
  public int compareTo(@NotNull HuffmanNode o) {
    int compValue = 0;
    if(this.mFrequency > o.getFrequency()) compValue = 1;
    if(this.mFrequency < o.getFrequency()) compValue = -1;
    return compValue;
  }
  
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
