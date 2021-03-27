class BinarySearchTree(object):
    class _BSTNode(object):

        content = None
        left = None
        right = None

        def __init__(self, pContent):
            self.content = pContent
            self.left = BinarySearchTree()
            self.right = BinarySearchTree()

    def __init__(self):
        self._node = None

    def isEmpty(self):
        return self._node == None

    def insert(self, pContent):
        if pContent is not None:
            if self.isEmpty():
                self._node = self._BSTNode(pContent)
            elif pContent < self._node.content:
                self._node.left.insert(pContent)
            elif pContent > self._node.content:
                self._node.right.insert(pContent)

    def getLeftTree(self):
        if self.isEmpty():
            return None
        else:
            return self._node.left

    def getContent(self):
        if self.isEmpty():
            return None
        else:
            return self._node.content

    def getRightTree(self):
        if self.isEmpty():
            return None
        else:
            return self._node.right

    def remove(self, pContent):
        if self.isEmpty() or pContent is None:
            return

        if pContent < node.content:
            self._node.left.remove(pContent)
        elif pContent > node.content:
            self._node.right.remove(pContent)
        else:
            if self._node.left.isEmpty():
                if self._node.right.isEmpty():
                    node = None
                else:
                    node = self._getNodeOfRightSuccessor()
            elif node.right.isEmpty():
                node = self._getNodeOfLeftSuccessor()
            else:
                if self._getNodeOfRightSuccessor().left.isEmpty():
                    self._node.content = self._getNodeOfRightSuccessor().content
                    self._node.right = self._getNodeOfRightSuccessor().right
                else:
                    previous = self._node.right._ancestorOfSmallRight()
                    smallest = previous._node.left
                    self._node.content = smallest._node.content
                    previous.remove(smallest._node.content)

    def search(self, pContent):
        if self.isEmpty() or pContent is None:
            return None
        else:
            content = self.getContent()
            if pContent < content:
                return self.getLeftTree().search(pContent)
            elif pContent > content:
                return self.getRightTree().search(pContent)
            elif pContent == content:
                return content
            else:
                return None

    def _ancestorOfSmallRight(self):
        if self._getNodeOfLeftSuccessor().left.isEmpty():
            return self
        else:
            return self._node.left._ancestorOfSmallRight()

    def _getNodeOfLeftSuccessor(self):
        return self._node.left._node

    def _getNodeOfRightSuccessor(self):
        return self._node.right._node
