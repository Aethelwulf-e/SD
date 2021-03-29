class Mensagem:
    def __init__(self, messageType, requestID, methodID, objectReference, arguments):
        self.messageType = messageType
        self.requestID = requestID
        self.objectReference = objectReference
        self.methodID = methodID
        self.arguments = arguments

    def toString(self):
        return self.messageType + ',' + self.requestID + ',' + self.methodID + ',' + self.objectReference + ',' + self.arguments