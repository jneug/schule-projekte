# -*- coding: utf-8 -*-


class Stack(object):
    class StackNode(object):
        def __init__(self, pContent):
            self._content = pContent
            self._nextNode = None

        def setNext(self, pNext):
            self._nextNode = pNext

        def getNext(self):
            return self._nextNode

        def getContent(self):
            return self._content

    def __init__(self):
        self._head = None

    def isEmpty(self):
        return self._head is None

    def push(self, pContent):
        if pContent is not None:
            node = self.StackNode(pContent)
            node.setNext(self._head)
            self._head = node

    def pop(self):
        if not self.isEmpty():
            self._head = self._head.getNext()

    def top(self):
        if not self.isEmpty():
            return self._head.getContent()
        else:
            return None
