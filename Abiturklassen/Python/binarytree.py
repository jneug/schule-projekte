# -*- coding: utf-8 -*-


class BinaryTree(object):
    class _BTNode(object):

        content = None
        left    = None
        right   = None

        def __init__(self, pContent):
            self.content = pContent
            self.left = BinaryTree()
            self.right = BinaryTree()

    def __init__(self, pContent=None, pLeftTree=None, pRightTree=None):
        if pContent is not None:
            self._node = self._BTNode(pContent)
            if isinstance(pLeftTree, BinaryTree):
                self._node.left = pLeftTree
            else:
                self._node.left = BinaryTree()

            if isinstance(pRightTree, BinaryTree):
                self._node.right = pRightTree
            else:
                self._node.right = BinaryTree()
        else:
            self._node = None

    def isEmpty(self):
        return self._node == None

    def setContent(self, pContent):
        if pContent is not None:
            if self.isEmpty():
                self._node = self._BTNode(pContent)
                self._node.left = BinaryTree()
                self._node.right = BinaryTree()
            self._node.content = pContent

    def getContent(self):
        if self.isEmpty():
            return None
        else:
            return self._node.content

    def setLeftTree(self, pTree):
        if not self.isEmpty() and isinstance(pTree, BinaryTree):
            self._node.left = pTree

    def setRightTree(self, pTree):
        if not self.isEmpty() and isinstance(pTree, BinaryTree):
            self._node.right = pTree

    def getLeftTree(self):
        if not self.isEmpty():
            return self._node.left
        else:
            return None

    def getRightTree(self):
        if not self.isEmpty():
            return self._node.right
        else:
            return None
