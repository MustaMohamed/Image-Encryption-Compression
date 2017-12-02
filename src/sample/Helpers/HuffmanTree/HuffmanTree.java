package sample.Helpers.HuffmanTree;

import sample.Helpers.CustomExceptions.TreeNotBuiltException;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.Vector;

/**
 * Instance Class for Huffman tree Data Structure
 */
public class HuffmanTree {
  private HuffmanNode mRootNode;
  private int mNodeCounter;
  private PriorityQueue<HuffmanNode> mPQNodeSelector;
  private Map<Integer, Long> mNodeFrequency;
  private Vector<HuffmanNode> mTreeNodes;
  private Map<Integer, String> mTreePaths;
  private String mTreeCode;
  private boolean isBuilt;
  public HuffmanTree() {
    this.mRootNode = null;
    this.mNodeCounter = 0;
    this.mTreeNodes = new Vector<HuffmanNode>();
    mNodeFrequency = new TreeMap<Integer, Long>();
  }
  
  public HuffmanTree(HuffmanNode rootNode, Map<Integer, Long> nodeFrequency, Vector<HuffmanNode> treeNodes) {
    this.mRootNode = rootNode;
    this.mNodeFrequency = nodeFrequency;
    this.mTreeNodes = treeNodes;
  }
  
  public String getTreeCode() {
    return mTreeCode;
  }
  
  public int getNodeCounter() {
    return this.mNodeCounter;
  }
  
  public HuffmanNode getRootNode() {
    return mRootNode;
  }
  
  public void setRootNode(HuffmanNode rootNode) {
    this.mRootNode = rootNode;
  }
  
  public Map<Integer, Long> getNodeFrequency() {
    return mNodeFrequency;
  }
  
  public void setNodeFrequency(Map<Integer, Long> nodeFrequency) {
    this.mNodeFrequency = nodeFrequency;
  }
  
  public Vector<HuffmanNode> getTreeNodes() {
    return mTreeNodes;
  }
  
  public void setTreeNodes(Vector<HuffmanNode> treeNodes) {
    this.mTreeNodes = mTreeNodes;
  }
  
  public void addNode(HuffmanNode node) {
    mTreeNodes.add(node);
    mNodeFrequency.put(node.getNodeValue(), node.getFrequency());
  }
  
  
  public long getNodeFrequency(int nodeValue) {
    return mNodeFrequency.get(nodeValue);
  }
  
  public String getNodePath(int nodeValue) throws TreeNotBuiltException {
    if(!isBuilt) throw new TreeNotBuiltException("The Tree is Not built...!");
    return mTreePaths.get(nodeValue);
  }
  
  
  /**
   * =====================================
   *  building tree section
   * =====================================
   */
  public void buildTree() {
    // initialize the priority queue frequencies
    initPQ();
    
    // begin to generate the tree structure
    initSelection();
    
    // build nodes paths
    buildPaths();
    
    this.isBuilt = true;
  }
  
  private void initPQ() {
    this.mPQNodeSelector = new PriorityQueue<>();
    this.mPQNodeSelector.addAll(mTreeNodes);
  }
  
  private void initSelection() {
    while (mPQNodeSelector.size() > 1) {
      HuffmanNode rightNode = mPQNodeSelector.poll();
      HuffmanNode leftNode = mPQNodeSelector.poll();
      
      HuffmanNode parentNode = new HuffmanNode(0,
        rightNode.getFrequency() + leftNode.getFrequency(),
        leftNode, rightNode);
      mPQNodeSelector.add(parentNode);
      this.mNodeCounter++;
    }
    this.mNodeCounter += mTreeNodes.size();
    this.mRootNode = mPQNodeSelector.poll();
  }
  
  private void buildPaths() {
    
    mTreePaths = new TreeMap<>();
    // generate node paths
    searchPath(mRootNode, new StringBuilder());
    // convert paths to char
    convertPaths();
  }
  
  private void searchPath(HuffmanNode node, StringBuilder path) {
    if (node.isLeaf()) {
      mTreePaths.put(node.getNodeValue(), path.toString());
      return;
    }
    // go to right node and add 1 to the path code
    path.append('0');
    searchPath(node.getRightNode(), path);
    path.deleteCharAt(path.length() - 1);
    // go to left node and add 0 to the path code
    path.append('1');
    searchPath(node.getLeftNode(), path);
    path.deleteCharAt(path.length() - 1);
  }
  
  private void convertPaths() {
    StringBuilder pathCode = new StringBuilder();
    for (String tempstr : mTreePaths.values())
      pathCode.append(tempstr);
    mTreeCode = pathCode.toString();
  }
  
  
  @Override
  public String toString() {
    return "HuffmanTree{" +
      "\nmRootNode=" + mRootNode +
      "\n, mNodeCounter=" + mNodeCounter +
      "\n, mPQNodeSelector=" + mPQNodeSelector +
      "\n, mNodeFrequency=" + mNodeFrequency +
      "\n, mTreeNodes=" + mTreeNodes +
      "\n, mTreePaths=" + mTreePaths +
      "\n, mTreeCode='" + mTreeCode + '\'' +
      '}';
  }
}

