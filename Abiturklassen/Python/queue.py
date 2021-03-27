# -*- coding: utf-8 -*-


class Queue(object):
    class QueueNode(object):
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
        self._tail = None

    def isEmpty(self):
        return self._head == None

    def enqueue(self, pContent):
        if pContent is not None:
            newNode = self.QueueNode(pContent)
            if self.isEmpty():
                self._head = newNode
                self._tail = newNode
            else:
                self._tail.setNext(newNode)
                self._tail = newNode

    def dequeue(self):
        if not self.isEmpty():
            self._head = self._head.getNext()
            if self.isEmpty():
                self._head = None
                self._tail = None

    def front(self):
        if self.isEmpty():
            return None
        else:
            return self._head.getContent()
