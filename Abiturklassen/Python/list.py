# -*- coding: utf-8 -*-


class List(object):
    class ListNode(object):
        def __init__(self, pContent):
            self._contentObject = pContent
            self._next = None

        def getContentObject(self):
            return self._contentObject

        def setContentObject(self, pContent):
            self._contentObject = pContent

        def getNextNode(self):
            return self._next

        def setNextNode(self, pNext):
            self._next = pNext

    def __init__(self):
        self._first = None
        self._last = None
        self._current = None

    def isEmpty(self):
        return self._first is None

    def hasAccess(self):
        return self._current is not None

    def next(self):
        if self.hasAccess():
            self._current = self._current.getNextNode()

    def toFirst(self):
        if not self.isEmpty():
            self._current = self._first

    def toLast(self):
        if not self.isEmpty():
            self._current = self._last

    def getContent(self):
        if self.hasAccess():
            return self._current.getContentObject()
        else:
            return None

    def setContent(self, pContent):
        if pContent is not None and self.hasAccess():
            self._current.setContentObject(pContent)

    def insert(self, pContent):
        if pContent is not None:
            if self.hasAccess():
                newNode = self.ListNode(pContent)

                if self._current != self._first:
                    previous = self._getPrevious(self._current)
                    newNode.setNextNode(previous.getNextNode())
                    previous.setNextNode(newNode)
                else:
                    newNode.setNextNode(self._first)
                    self._first = newNode

            else:
                if self.isEmpty():
                    newNode = self.ListNode(pContent)

                    self._first = newNode
                    self._last = newNode

    def append(self, pContent):
        if pContent is not None:
            if self.isEmpty():
                self.insert(pContent)
            else:
                newNode = self.ListNode(pContent)

                self._last.setNextNode(newNode)
                self._last = newNode

    def concat(self, pList):
        if pList != self and pList and not pList.isEmpty():
            if self.isEmpty():
                self._first = pList._first
                self._last = pList._last
            else:
                self._last.setNextNode(pList._first)
                self._last = pList._last

            del pList

    def remove(self):
        if self.hasAccess() and not self.isEmpty():
            if self._current == self._first:
                self._first = self._first.getNextNode()
            else:
                previous = self._getPrevious(self._current)
                if self._current == self._last:
                    self._last = previous
                previous.setNextNode(self._current.getNextNode())

            temp = self._current.getNextNode()
            self._current.setContentObject(None)
            self._current.setNextNode(None)
            self._current = temp

            if self.isEmpty():
                last = None

    def _getPrevious(self, pNode):
        if pNode is not None and pNode != self._first and not self.isEmpty():
            temp = self._first
            while temp and temp.getNextNode() is not pNode:
                temp = temp.getNextNode()
            return temp
        else:
            return None
