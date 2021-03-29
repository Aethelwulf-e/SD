public class Mensagem {
    private int messageType;
    private int requestID;
    private int methodID;
    private String objectReference;
    private String arguments;

    public Mensagem() {}

    public Mensagem(int messageType, int requestID, int methodID,
                    String objectReference, String arguments) {
        this.messageType = messageType;
        this.requestID = requestID;
        this.methodID = methodID;
        this.objectReference = objectReference;
        this.arguments = arguments;
    }

    public String toString() {
        String _messageType = String.valueOf(this.messageType);
        String _requestID = String.valueOf(this.requestID);
        String _methodID = String.valueOf(this.methodID);
        String _objectReference = this.objectReference;
        String _arguments = this.arguments;

        return _messageType + "," + _requestID + "," + _objectReference +
                "," + _methodID + "," + _arguments;
    }
}
