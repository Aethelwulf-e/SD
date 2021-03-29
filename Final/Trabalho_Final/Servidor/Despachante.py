from Locadora import *
from Mensagem import *

class Despachante:
    def __init__(self):
        self.locadora = Locadora()

    def invoke(self, msg):
        values = msg.split(",")

        if(len(values) < 5 or values[2] != 'Locadora'):
            return 'Fracasso'

        messageType = values[0]
        requestID = values[1]
        objectReference = values[2]
        methodID = values[3]
        arguments = values[4].split(';')

        if methodID == "1":
            result = self.locadora.cadastrarCarro(arguments[0], arguments[1], arguments[2].replace('"', ''))
            return Mensagem(messageType, requestID, objectReference, methodID, result).toString()

        if methodID == "2":
            result = self.locadora.cadastrarCliente(arguments[0], arguments[1].replace('"', ''))
            return Mensagem(messageType, requestID, objectReference, methodID, result).toString()

        if methodID == "3":
            arguments = values[4]
            result = self.locadora.deletarCarro(arguments.replace('"', ''))
            return Mensagem(messageType, requestID, objectReference, methodID, result).toString()

        if methodID == "4":
            arguments = values[4]
            result = self.locadora.deletarCliente(arguments.replace('"', ''))
            return Mensagem(messageType, requestID, objectReference, methodID, result).toString()

        if methodID == "5":
            result = self.locadora.getAllCarros()
            return Mensagem(messageType, requestID, objectReference, methodID, result).toString()

        if methodID == "6":
            result = self.locadora.getAllClientes()
            return Mensagem(messageType, requestID, objectReference, methodID, result).toString()

        if methodID == "7":
            result = self.locadora.getEstoque()
            return Mensagem(messageType, requestID, objectReference, methodID, result).toString()

        if methodID == "8":
            result = self.locadora.getCarrosAlugados()
            return Mensagem(messageType, requestID, objectReference, methodID, result).toString()

        if methodID == "9":
            result = self.locadora.getHistorico()
            return Mensagem(messageType, requestID, objectReference, methodID, result).toString()

        if methodID == "10":
            arguments = values[4].split(";")
            result = self.locadora.alugarCarro(arguments[0], arguments[1].replace('"', ''))
            return Mensagem(messageType, requestID, objectReference, methodID, result).toString()

        if methodID == "11":
            arguments = values[4].split(";")
            result = self.locadora.devolverCarro(arguments[0], arguments[1], arguments[2].replace('"', ''))
            return Mensagem(messageType, requestID, objectReference, methodID, result).toString()

