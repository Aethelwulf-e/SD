class Alugado:
    def __init__(self, modelo, placa, cpf_cliente, saida):
        self.modelo      = modelo
        self.placa       = placa
        self.cpf_cliente = cpf_cliente
        self.saida       = saida

    def getModel(self):
        return self.modelo

    def get_placa(self):
        return self.placa

    def get_cpfCliente(self):
        return self.cpf_cliente

    def get_saida(self):
        return self.saida

    def toString(self):
        showModelo = 'Modelo: ' + str(self.modelo) + '\n'
        showPlaca  = 'Placa: ' + str(self.placa) + '\n'
        showCPF    = 'CPF associado: ' + str(self.cpf_cliente) + '\n'
        showSaida  = 'Data de saida: ' + str(self.saida) + '\n'

        string = showModelo + showPlaca + showCPF + showSaida

        return string