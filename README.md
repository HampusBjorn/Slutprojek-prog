Programmerings slutprojekt
 
Abstract:
Projektet är att vidare utveckla ett krypteringsprogram för att ha en fortsatt anonym tillvaro. 
Det som gjordes för att försäkra sig av detta var att göra så att ingen har en likadan lösenords nyckel och det gjordes så att det är enklare än någonsin att kryptera sin text med hjälp av detta program.
Det gick bra att göra alla nya funktioner och metoder. I slutänden så finns alla saker där och jag har lärt mig väldigt mycket.
 
Syftet med arbetet är att vidare utveckla ett krypteringsprogram.

Bakgrund:
Dagens samhälle blir mer och mer övervakat och det blir svårare att hålla saker hemligt för andra människor. Dagligen utvecklas nya sätt att avkryptera det folk skriver till varandra så därför måste krypterings programmen också utvecklas.

Beskrivning av lösning:
Programmet som jag skulle vidareutveckla är ett krypteringsprogram som har grunderna. Man kunde ansluta till en server, man kunde kryptera en text och avkryptera det, man kunde skriva in en egen nyckel som man använder för krypteringen.

Några problem som fanns i programmet var delvis att varje gång man krypterade något så blev man frånkopplad från servern.

De saker jag gjorde på programmet var att fixa så att programmet blev mer användarvänligt, jag löste problemet med att man blev frånkopplad och jag fixade så att man kan autogenerera en lösenordsnyckel till krypteringen.

Problemet med att man blev frånkopplat löste jag med att när man krypterar en text så kommer du automatiskt att ansluta till servern igen i bakgrunden. Detta gjorde jag med hjälp av att när man klickar på Crypt knappen så körs en if statment som återansluter dig till server.
Användarvänligheten löste jag med att lägga till så att standard inställningarna för att ansluta till servern är redan ifyllda så att man inte behöver ta reda på vad porten och ipn till servern är 
Och sist men inte minst så har vi att autogenerera en nyckel. Detta löste jag med att lägga till en ny knapp som heter generate, när man klickar på den knappen så körs en metod som genererar en string som är mellan 1-20 bokstäver lång och sedan placeras denna text i rutan där man kan skriva in sin nyckel.
Kod för återanslutning när knapp trycks:
class CryptListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!view.getKey().isEmpty() && !view.getMsg().isEmpty()) {
            view.setCryptArea(client.crypt(view.getMsg(), view.getKey()));
            client.connect(view.getIp(), view.getPort()); }
        else JOptionPane.showMessageDialog(null, "Du måste ange en nyckel och meddelande");
    }
}

Kod för generering av en random string:
public String generateKey(){
    final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    int count = (int) ((Math.random() * 15) + 5);
    StringBuilder builder = new StringBuilder();
    while (count-- != 0) {
        int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
        builder.append(ALPHA_NUMERIC_STRING.charAt(character));
    }
    return builder.toString();
}

•	För att använda programmet så måste både Server.java och filen Main.java
sedan så för att ansluta till servern så måste du bara trycka på Connect knappen.
Sedan för att börja kryptera din text, måste du skriva en nyckel, du kan antingen skriva en egen nyckel eller generera en nyckel. När detta är gjort så är det bara att fylla i det stora vänstra text fältet med texten du vill kryptera/avkryptera och klicka knappen där det står crypt. Resultatet kommer dycka upp i det högra stora text fältet.

Test körning:
Allt funkar som det ska

Diskussion:
Jag tycker detta var ett svårt projekt eftersom min kunskap inom programmering är väldigt begränsad men jag känner att jag har lärt mig saker och nu vet jag tillexempel hur ett GUI fungerar.
Nu tror jag till och med att jag skulle kunna skriva ett sånt här program helt från grunden själv.

