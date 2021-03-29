import psycopg2 as pdb
import psycopg2.extras
import warnings

from Carro import *
from Cliente import *
from Alugado import *
from Historico import *

class Locadora:
    def __init__(self):
        try:
            self.con = pdb.connect(database="Locadora", user="postgres",
                                    password="phoenixfly", host="127.0.0.1", port="5432")

            self.cur = self.con.cursor()
            self.cur.execute('SELECT version()')
            self.ver = self.cur.fetchall()
            print('Connected with database!\n' + str(self.ver))

        except pdb.DatabaseError:
            print('Error while starting database')

    def cadastrarCarro(self, modelo, placa, preco):
        with self.con:
            try:
                self.cur = self.con.cursor()
                self.cur.execute('SELECT cadastrar_carro(%s, %s, %s)', (modelo, placa, preco))
                print('Car Record Inserted')
                self.con.commit()
                return 'Sucesso'
                
            except Exception as e:
                print('Error while inserting new car: ' + str(e))
                return 'Fracasso'

    def cadastrarCliente(self, nome, cpf):
        with self.con:
            try:
                self.cur = self.con.cursor()
                self.cur.execute('SELECT cadastrar_cliente(%s, %s)', (nome, cpf))
                print('Client Record Inserted')
                self.con.commit()
                return 'Sucesso'
            except Exception as e:
                print('Error while inserting new client: ' + str(e))
                return 'Fracasso'

    def deletarCarro(self, placa):
        with self.con:
            try:
                self.cur = self.con.cursor()
                self.cur.execute('SELECT remove_carro(%s)', (placa,))
                print('Car Record Deleted')
                self.con.commit()
                return 'Sucesso'
            except Exception as e:
                print('Error while deleting car: ' + str(e))
                return 'Fracasso'

    def deletarCliente(self, cpf):
        with self.con:
            try:
                self.cur = self.con.cursor()
                self.cur.execute('SELECT remove_cliente(%s)', (cpf,))
                print('Client Record Deleted')
                self.con.commit()
                return 'Sucesso'
            except Exception as e:
                print('Error while deleting client: ' + str(e))
                return 'Fracasso'

    def getAllClientes(self):
        with self.con:
            try:
                self.cur = self.con.cursor(cursor_factory=pdb.extras.DictCursor)
                self.cur.execute('SELECT * FROM Cliente')
                rows = self.cur.fetchall()
                clientes = ""

                for row in rows:
                    if row is not None:
                        cliente = Cliente(row[0], row[1])
                        clientes = clientes + cliente.toString() + ';'

                return clientes

            except Exception as e:
                print('Error while getting all clients: ' + str(e))
                return 'Fracasso'

    def getAllCarros(self):
        with self.con:
            try:
                self.cur = self.con.cursor(cursor_factory=pdb.extras.DictCursor)
                self.cur.execute('SELECT pegar_todos_os_carros()')
                rows = self.cur.fetchall()
                carros = ""

                for row in rows:
                    if row is not None:
                        values = row[0].split(",")

                        val1 = values[0][:0] + values[0][1:]
                        val2 = values[1]
                        val3 = values[2][:len(values[2])-1] + values[2][len(values[2]):]

                        carro = Carro(val2, val1, val3)
                        carros = carros + carro.toString() + ';'

                return carros

            except Exception as e:
                print('Error while retrieving all cars: ' + str(e))
                return 'Fracasso'

    def getCarrosAlugados(self):
        with self.con:
            try:
                self.cur = self.con.cursor(cursor_factory=pdb.extras.DictCursor)
                self.cur.execute('SELECT pegar_carros_alugados()')
                rows = self.cur.fetchall()
                carros_alugados = ""

                for row in rows:
                    if row is not None:
                        values = row[0].split(",")

                        val1 = values[0][:0] + values[0][1:]
                        val2 = values[1]
                        val3 = values[2]
                        val4 = values[3][:len(values[3])-1] + values[3][len(values[3]):]

                        alugado = Alugado(val1, val2, val3, val4)
                        carros_alugados = carros_alugados + alugado.toString() + ';'

                return carros_alugados

            except Exception as e:
                print('Error while getting rented cars: ' + str(e))
                return 'Fracasso'

    def getEstoque(self):
        with self.con:
            try:
                self.cur = self.con.cursor(cursor_factory=pdb.extras.DictCursor)
                self.cur.execute("SELECT pegar_estoque()")
                rows = self.cur.fetchall()
                carros = ""

                for row in rows:
                    if row is not None:
                        values = row[0].split(",")

                        val1 = values[0][:0] + values[0][1:]
                        val2 = values[1]
                        val3 = values[2][:len(values[2])-1] + values[2][len(values[2]):]

                        carro = Carro(val2, val1, val3)
                        carros = carros + carro.toString() + ';'

                return carros

            except Exception as e:
                print('Error while retrieving cars: ' + str(e))
                return 'Fracasso'

    def alugarCarro(self, cpf, placa):
        with self.con:
            try:
                self.cur = self.con.cursor(cursor_factory=pdb.extras.DictCursor)
                self.cur.execute('SELECT alugar_carro(%s, %s)', (cpf, placa))
                result = self.cur.fetchall()

                result = result[0][0]

                if result == 1:
                    return 'Cliente nao encontrado'
                elif result == 2:
                    return 'Carro nao encontrado'
                elif result == 3:
                    return 'Carro alugado com Sucesso'
                elif result == 0:
                    return 'Carro ja esta alugado'
                else:
                    return 'Fracasso'

            except Exception as e:
                print('Error while renting a car: ' + str(e))
                return 'Fracasso'

    def devolverCarro(self, cpf, placa, data):
        with self.con:
            try:
                self.cur = self.con.cursor(cursor_factory=pdb.extras.DictCursor)
                self.cur.execute('SELECT devolver_carro(%s, %s, %s)', (cpf, placa, data))
                result = self.cur.fetchall()

                result = result[0][0]

                if result == "-1":
                    return 'Fracasso'
                else:
                    return result

            except Exception as e:
                print('Error while returning car: ' + str(e))
                return 'Fracasso'

    def getHistorico(self):
        with self.con:
            try:
                self.cur = self.con.cursor(cursor_factory=pdb.extras.DictCursor)
                self.cur.execute('SELECT * FROM Historico')
                rows = self.cur.fetchall()

                historicos = ""

                for row in rows:
                    if row is not None:
                        historico = Historico(row[0], row[1], row[2], row[3], row[4])
                        historicos = historicos + historico.toString() + ';'

                return historicos

            except Exception as e:
                print('Error while getting history: ' + str(e))
                return 'Fracasso'
