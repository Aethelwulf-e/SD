class Cliente:
    def __init__(self, nome, CPF):
        self.nome = nome
        self.CPF  = CPF

    def get_nome(self):
        return self.nome

    def get_CPF(self):
        return self.CPF

    def toString(self):
        showNome = 'Nome: ' + str(self.nome) + '\n'
        showCPF  = 'CPF: ' + str(self.CPF) + '\n'
        
        string = showNome + showCPF

        return string