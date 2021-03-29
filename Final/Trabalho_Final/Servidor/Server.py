import socket
import json

from Despachante import *

class Server:
    def __init__(self):
        self.host = '127.0.0.1'
        self.port = 4321
        self.despachante = Despachante()
        self.s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.s.bind((self.host, self.port))
        self.s.listen(5)
        self.connectionSocket, self.addr = self.s.accept()

    def getRequest(self):
        #self.connectionSocket, self.addr = self.s.accept()
        return self.connectionSocket.recv(1024).decode()

    def sendReply(self, msg):
        result = self.despachante.invoke(msg)
        self.connectionSocket.send(json.dumps(result).encode())
        self.connectionSocket.send('\n'.encode())

    def reconnect(self):
        self.connectionSocket, self.addr = self.s.accept()

    def close(self):
        self.connectionSocket.close()

    def closeall(self):
        self.connectionSocket.close()
        self.s.close()

def Main():
    try:
        print('Ready to server...')
        server = Server()
        while True:
            while True:
                msg = server.getRequest()
                if(msg == '"exit"'):
                    server.close()
                    break
                server.sendReply(msg)
            server.reconnect()

    except Exception as e:
        print('Error while trying to connect to the Client: ' + str(e))
        print('Closing socket...')
        server.closeall()

if __name__ == '__main__':
    Main()
