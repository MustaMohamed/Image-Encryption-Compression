package sample.Helpers.HuffmanTree;

import sample.Helpers.CustomExceptions.TreeNotBuiltException;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.Vector;

/**
 * Instance Class for Huffman tree Data Structure
 *
 * @author Musta Mohamed
 */
public class HuffmanTree {
  
  // the rood node that holds the tree
  private HuffmanNode mRootNode;
  // the number of nodes in tree
  private int mNodeCounter;
  // the priority queue data structure to get the minimum node
  private PriorityQueue<HuffmanNode> mPQNodeSelector;
  // the nodes frequencies
  private Map<Integer, Long> mNodeFrequency;
  // the tree nodes members
  private Vector<HuffmanNode> mTreeNodes;
  // the path of each leaf node in tree
  private Map<Integer, String> mTreePaths;
  // the final tree code in binary string
  private String mTreeCode;
  // the flag of tree building
  private boolean isBuilt;
  
  /**
   * =====================================
   *  Data members of the tree section
   * =====================================
   */
  
  /**
   * Empty Constructor initialize the data members
   */
  public HuffmanTree() {
    this.mRootNode = null;
    this.mNodeCounter = 0;
    this.mTreeNodes = new Vector<HuffmanNode>();
    mNodeFrequency = new TreeMap<Integer, Long>();
  }
  
  /**
   * Object Constructor copy a tree from other tree
   * @param rootNode        HuffmanNode           the tree root node that hold the tree
   * @param nodeFrequency   Map<Integer, Long>    the tree nodes frequencies
   * @param treeNodes       Vector<HuffmanNode>   the tree nodes list
   */
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
  
  /**
   * Adds new Node to the tree nodes list
   * @param node    HuffmanNode   the new node
   */
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
  
  /**
   * this function begins the tree building operations
   */
  public void buildTree() {
    // initialize the priority queue frequencies
    initPQ();
    
    // begin to generate the tree structure
    initSelection();
    
    // build nodes paths
    buildPaths();
    
    // set the flag to be true
    this.isBuilt = true;
  }
  
  /**
   * copies all node in nodes list to the priority queue
   */
  private void initPQ() {
    this.mPQNodeSelector = new PriorityQueue<>();
    this.mPQNodeSelector.addAll(mTreeNodes);
  }
  
  /**
   * build the tree
   */
  private void initSelection() {
    while (mPQNodeSelector.size() > 1) {
      // get the minimum 2 node
      HuffmanNode rightNode = mPQNodeSelector.poll();
      HuffmanNode leftNode = mPQNodeSelector.poll();
      
      // merge the 2 nodes in parent node and set them to right and left nodes
      HuffmanNode parentNode = new HuffmanNode(0,
        rightNode.getFrequency() + leftNode.getFrequency(),
        leftNode, rightNode);
      // add the parent node to PQ
      mPQNodeSelector.add(parentNode);
      // increment the nodes counter
      this.mNodeCounter++;
    }
    this.mNodeCounter += mTreeNodes.size();
    // the final node is the root node
    this.mRootNode = mPQNodeSelector.poll();
  }
  
  /**
   * building the tree paths for each node
   */
  private void buildPaths() {
    
    mTreePaths = new TreeMap<>();
    // generate node paths
    searchPath(mRootNode, new StringBuilder());
    // convert paths to char
    //convertPaths();
  }
  
  /**
   * generate the node paths in the tree and put them in paths table
   * @param node    HuffmanNode     the current node in tree
   * @param path    StringBuilder   the string that contains the node path
   */
  private void searchPath(HuffmanNode node, StringBuilder path) {
    if (node.isLeaf()) {
      // the end of the path and returns
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
  
  /**
   * generate the tree code for all paths
   */
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

