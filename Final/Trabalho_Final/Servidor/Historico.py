class Historico:
    def __init__(self, cpf_cliente, placa, saida, entrada, valor):
        self.cpf_cliente = cpf_cliente
        self.placa       = placa
        self.entrada     = entrada
        self.saida       = saida
        self.valor       = valor

    def get_cpfClient(self):
        return self.cpf_cliente

    def get_placa(self):
        return self.placa

    def get_saida(self):
        return self.saida

    def get_entrada(self):
        return self.entrada

    def get_valor(self):
        return self.valor

    def toString(self):
        showCPF     = 'CPF associado: ' + str(self.cpf_cliente) + '\n'
        showPlaca   = 'Placa do carro: ' + str(self.placa) + '\n'
        showSaida   = 'Data de saida: ' + str(self.saida) + '\n'
        showEntrada = 'Data de entrada: ' + str(self.entrada) + '\n'
        showValor   = 'Valor final: ' + str(self.valor) + '\n'
        
        string = showCPF + showPlaca + showSaida + showEntrada + showValor

        return string