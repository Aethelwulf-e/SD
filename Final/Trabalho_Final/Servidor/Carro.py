class Carro:
    def __init__(self, placa, modelo, preco):
        self.placa  = placa
        self.modelo = modelo
        self.preco  = preco

    def get_placa(self):
        return self.placa

    def get_modelo(self):
        return self.modelo

    def get_preco(self):
        return self.preco

    def toString(self):
        showPlaca  = 'Placa: ' + str(self.placa) + '\n'
        showModelo = 'Modelo: ' + str(self.modelo) + '\n'
        showPreco  = 'Preco: ' + str(self.preco) + '\n'

        string = showPlaca + showModelo + showPreco

        return string
