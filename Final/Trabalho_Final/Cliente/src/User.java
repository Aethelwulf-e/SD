import javax.swing.*;

//JOptionPane.showMessageDialog(null, "texto da caixa","titulo da caixa",JOptionPane.ERROR_MESSAGE,new ImageIcon("c:\minhafoto.png"));

public class User {
    private Cliente cliente = null;
    private Mensagem mensagem = null;
    private boolean keepRunning;
    private ImageIcon icon;
    private int id;

    public static void main(String args[]) {
        User user = new User();

        while(user.runner()) {
            user.init(user.menu());
        }
    }

    public User() {
        id = 0;
        mensagem = new Mensagem();
        cliente = new Cliente();
        keepRunning = true;
        icon = new ImageIcon("/home/carlos/Desktop/Client-side/src/icon.png");
    }

    public boolean runner() {
        return this.keepRunning;
    }

    public void setRunner(boolean runner) {
        this.keepRunning = runner;
    }

    public String menu() {
        String options =
                        "1 - Cadastrar Carro\n" +
                        "2 - Cadastrar Cliente\n" +
                        "3 - Deletar Carro\n" +
                        "4 - Deletar Cliente\n" +
                        "5 - Mostrar carros cadastrados\n" +
                        "6 - Mostrar clientes cadastrados\n" +
                        "7 - Mostrar carros disponíveis\n" +
                        "8 - Mostrar carros alugados\n" +
                        "9 - Mostrar histórico dos clientes\n" +
                        "10 - Alugar carro\n" +
                        "11 - Devolver carro\n" +
                        "0 - Sair";

        return JOptionPane.showInputDialog(null, options,
                "Escolha uma opção", JOptionPane.INFORMATION_MESSAGE, icon, null, "").toString();
    }

    public void init(String op) {
        switch(op) {
            case "1":
            {
                String modelo = JOptionPane.showInputDialog(null, "Entre com o modelo do carro",
                        "Input", JOptionPane.INFORMATION_MESSAGE);

                String placa = JOptionPane.showInputDialog(null, "Entre com a placa do carro",
                        "Input", JOptionPane.INFORMATION_MESSAGE);

                String preco= JOptionPane.showInputDialog(null, "Entre com o preço por hora",
                        "Input", JOptionPane.INFORMATION_MESSAGE);

                String arguments = modelo + ";" + placa + ";" + preco;



                this.cliente.doOperation("Locadora", op, arguments, this.id++);

                String reply = this.cliente.getResponse();

                String [] replySplit = reply.split(",");

                if(!replySplit[1].equals(String.valueOf(this.id-1))) {
                    reply = "Fracasso";
                } else {
                    reply = replySplit[4];

                    if(reply.length() == 0) {
                        reply = "Vazio";
                    }
                }

                JOptionPane.showMessageDialog(null, reply, "Output", JOptionPane.INFORMATION_MESSAGE);

                break;
            }
            case "2":
            {
                String nome = JOptionPane.showInputDialog(null, "Entre com o nome",
                        "Input", JOptionPane.INFORMATION_MESSAGE);

                String cpf = JOptionPane.showInputDialog(null, "Entre com o CPF",
                        "Input", JOptionPane.INFORMATION_MESSAGE);

                String arguments = nome + ";" + cpf;



                this.cliente.doOperation("Locadora", op, arguments, this.id++);

                String reply = this.cliente.getResponse();

                String [] replySplit = reply.split(",");

                if(!replySplit[1].equals(String.valueOf(this.id-1))) {
                    reply = "Fracasso";
                } else {
                    reply = replySplit[4];

                    if(reply.length() == 0) {
                        reply = "Vazio";
                    }
                }

                if(reply.length() == 0) {
                    reply = "Vazio";
                }

                JOptionPane.showMessageDialog(null, reply, "Output", JOptionPane.INFORMATION_MESSAGE);

                break;
            }
            case "3":
            {
                String placa = JOptionPane.showInputDialog(null, "Entre com a placa",
                        "Input", JOptionPane.INFORMATION_MESSAGE);

                String arguments = placa;



                this.cliente.doOperation("Locadora", op, arguments, this.id++);

                String reply = this.cliente.getResponse();

                String [] replySplit = reply.split(",");

                if(!replySplit[1].equals(String.valueOf(this.id-1))) {
                    reply = "Fracasso";
                } else {
                    reply = replySplit[4];

                    if(reply.length() == 0) {
                        reply = "Vazio";
                    }
                }

                if(reply.length() == 0) {
                    reply = "Vazio";
                }

                JOptionPane.showMessageDialog(null, reply, "Output", JOptionPane.INFORMATION_MESSAGE);

                break;
            }
            case "4":
            {
                String cpf = JOptionPane.showInputDialog(null, "Entre com o CPF",
                        "Input", JOptionPane.INFORMATION_MESSAGE);

                String arguments = cpf;



                this.cliente.doOperation("Locadora", op, arguments, this.id++);

                String reply = this.cliente.getResponse();

                String [] replySplit = reply.split(",");

                if(!replySplit[1].equals(String.valueOf(this.id-1))) {
                    reply = "Fracasso";
                } else {
                    reply = replySplit[4];

                    if(reply.length() == 0) {
                        reply = "Vazio";
                    }
                }

                if(reply.length() == 0) {
                    reply = "Vazio";
                }

                JOptionPane.showMessageDialog(null, reply, "Output", JOptionPane.INFORMATION_MESSAGE);

                break;
            }
            case "5":
            {


                this.cliente.doOperation("Locadora", op, "", this.id++);

                String reply = this.cliente.getResponse();

                String [] replySplit = reply.split(",");

                if(!replySplit[1].equals(String.valueOf(this.id-1))) {
                    reply = "Fracasso";
                    JOptionPane.showMessageDialog(null, reply, "Carros cadastrados", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if(replySplit.length < 5) {
                        reply = "Vazio";
                        JOptionPane.showMessageDialog(null, reply, "Carros cadastrados", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        reply = replySplit[4];
                        String [] all = reply.split(";");
                        for(String v : all) {
                            JOptionPane.showMessageDialog(null, v, "Carros cadastrados", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }

                break;
            }
            case "6":
            {


                this.cliente.doOperation("Locadora", op, "", this.id++);

                String reply = this.cliente.getResponse();

                String [] replySplit = reply.split(",");

                if(!replySplit[1].equals(String.valueOf(this.id-1))) {
                    reply = "Fracasso";
                    JOptionPane.showMessageDialog(null, reply, "Clientes cadastrados", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if(replySplit.length < 5) {
                        reply = "Vazio";
                        JOptionPane.showMessageDialog(null, reply, "Clientes cadastrados", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        reply = replySplit[4];
                        String [] all = reply.split(";");
                        for(String v : all) {
                            JOptionPane.showMessageDialog(null, v, "Clientes cadastrados", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }

                break;
            }
            case "7":
            {


                this.cliente.doOperation("Locadora", op, "", this.id++);

                String reply = this.cliente.getResponse();

                String [] replySplit = reply.split(",");

                if(!replySplit[1].equals(String.valueOf(this.id-1))) {
                    reply = "Fracasso";
                    JOptionPane.showMessageDialog(null, reply, "Carros disponíveis", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if(replySplit.length < 5) {
                        reply = "Vazio";
                        JOptionPane.showMessageDialog(null, reply, "Carros disponíveis", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        reply = replySplit[4];
                        String [] all = reply.split(";");
                        for(String v : all) {
                            JOptionPane.showMessageDialog(null, v, "Carros disponíveis", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }

                break;
            }
            case "8":
            {


                this.cliente.doOperation("Locadora", op, "", this.id++);

                String reply = this.cliente.getResponse();

                String [] replySplit = reply.split(",");

                if(!replySplit[1].equals(String.valueOf(this.id-1))) {
                    reply = "Fracasso";
                    JOptionPane.showMessageDialog(null, reply, "Carros alugados", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if(replySplit.length < 5) {
                        reply = "Vazio";
                        JOptionPane.showMessageDialog(null, reply, "Carros alugados", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        reply = replySplit[4];
                        String [] all = reply.split(";");
                        for(String v : all) {
                            JOptionPane.showMessageDialog(null, v, "Carros alugados", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }

                break;
            }
            case "9":
            {


                this.cliente.doOperation("Locadora", op, "", this.id++);

                String reply = this.cliente.getResponse();

                String [] replySplit = reply.split(",");

                if(!replySplit[1].equals(String.valueOf(this.id-1))) {
                    reply = "Fracasso";
                    JOptionPane.showMessageDialog(null, reply, "Histórico", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if(replySplit.length < 5) {
                        reply = "Vazio";
                        JOptionPane.showMessageDialog(null, reply, "Histórico", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        reply = replySplit[4];
                        String [] all = reply.split(";");
                        for(String v : all) {
                            JOptionPane.showMessageDialog(null, v, "Histórico", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }

                break;
            }
            case "10":
            {
                String cpf = JOptionPane.showInputDialog(null, "Entre com CPF",
                        "Input", JOptionPane.INFORMATION_MESSAGE);

                String placa = JOptionPane.showInputDialog(null, "Entre com a placa",
                        "Input", JOptionPane.INFORMATION_MESSAGE);

                String arguments = cpf + ";" + placa;



                this.cliente.doOperation("Locadora", op, arguments, this.id++);

                String reply = this.cliente.getResponse();

                String [] replySplit = reply.split(",");

                if(!replySplit[1].equals(String.valueOf(this.id-1))) {
                    reply = "Fracasso";
                } else {
                    reply = replySplit[4];

                    if(reply.length() == 0) {
                        reply = "Vazio";
                    }
                }


                JOptionPane.showMessageDialog(null, reply, "Output", JOptionPane.INFORMATION_MESSAGE);

                break;
            }
            case "11":
            {
                String cpf = JOptionPane.showInputDialog(null, "Entre com CPF",
                        "Input", JOptionPane.INFORMATION_MESSAGE);

                String placa = JOptionPane.showInputDialog(null, "Entre com a placa",
                        "Input", JOptionPane.INFORMATION_MESSAGE);

                String data = JOptionPane.showInputDialog(null, "Entre com o dia da entrega",
                        "Input", JOptionPane.INFORMATION_MESSAGE);

                String arguments = cpf + ";" + placa + ";" + data;



                this.cliente.doOperation("Locadora", op, arguments, this.id++);

                String reply = this.cliente.getResponse();

                String [] replySplit = reply.split(",");

                if(!replySplit[1].equals(String.valueOf(this.id-1))) {
                    reply = "Fracasso";
                } else {
                    reply = replySplit[4];

                    if(reply.length() == 0) {
                        reply = "Vazio";
                    }
                }

                JOptionPane.showMessageDialog(null, reply, "Preço final", JOptionPane.INFORMATION_MESSAGE);

                break;
            }
            case "0":
            {
                this.setRunner(false);
                this.cliente.sendRequest("exit");
                String reply = "Até mais!";
                JOptionPane.showMessageDialog(null, reply, "Output", JOptionPane.INFORMATION_MESSAGE);
                this.cliente.close();
                break;
            }
            default:
            {
                JOptionPane.showMessageDialog(null, "Opcao Invalida!", "Output", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        }
    }
}