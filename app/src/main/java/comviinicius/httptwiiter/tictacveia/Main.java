package comviinicius.httptwiiter.tictacveia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.Toast;

public class Main extends AppCompatActivity {

    private View view;
    public final String BOLA = "O";
    public final String XIS = "X";
    public String lastPlayed = "X";
    public final String square = "quadrado";
    public int player_x = 0;
    public int player_o = 0;
    int[][] victory_conditions = new int[][]{

            {1,2,3}, // win conditions
            {4,5,6},
            {7,8,9},

            {1,4,7},
            {2,5,8},
            {3,6,9},

            {1,5,9},
            {3,5,7},
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(getLayoutInflater().inflate(R.layout.layout_main,null));
        setContentView(getView());
    }
    public void clickQuadrado(View v){

        if(((Button)v).getText().equals("") ){
            if(getLastPlayed().equals(XIS)){
                ((Button)v).setText(BOLA);
                setLastPlayed(BOLA);
                matchWin();
                gameWin();

            }else{
                ((Button)v).setText(XIS);
                setLastPlayed(XIS);
                matchWin();
                gameWin();
            }
        }
    }
    public void newGame(View v){
        eneableQuadrado(true);
        for(int index = 1;index<=9;index++){
            if(getQuadrado(index)!=null){
                getQuadrado(index).setText("");
            }
        }
        if(player_x==player_o && player_o != 0){
            Toast.makeText(getView().getContext(),"Empate",Toast.LENGTH_LONG).show();
        }
    }
    public void eneableQuadrado( boolean b){
        for(int index = 1;index<=9;index++){
            if(getQuadrado(index)!=null){
                getQuadrado(index).setEnabled(b);
            }
        }
    }
    public void gameWin( ){
        if(this.player_x==5){
            Toast.makeText(getView().getContext(),"Player X vitorioso. Parabens!",Toast.LENGTH_LONG).show();
            eneableQuadrado(false);
            this.player_x = 0;
            this.player_o = 0;
        }else{
            if (this.player_o == 5){
                Toast.makeText(getView().getContext(),"Player O vitorioso. Parabens!",Toast.LENGTH_LONG).show();
                eneableQuadrado(false);
                this.player_x = 0;
                this.player_o = 0;
            }
        }
    }
    public void matchWin(){

       for(int x =0 ; x<=7;++x){
           String s1=getQuadrado(victory_conditions[x][0]).getText().toString();
           String s2=getQuadrado(victory_conditions[x][1]).getText().toString();
           String s3=getQuadrado(victory_conditions[x][2]).getText().toString();

           if (!s1.equals("")&&!s2.equals("")&&!s3.equals("")){
               if(s1.equals(s2)&&(s2.equals(s3))){
                   if(s1.equals(XIS)){
                       addVitoriaX();
                       Toast.makeText(getView().getContext(), "player X Ganhou.", Toast.LENGTH_LONG).show();
                       eneableQuadrado(false);
                   }else {
                           addVitoriaO();
                           Toast.makeText(getView().getContext(),"player O Ganhou.",Toast.LENGTH_LONG).show();
                           eneableQuadrado(false);
                       }
                   getQuadrado(11).setText(Integer.toString(player_x));
                   getQuadrado(12).setText(Integer.toString(player_o));

               }
               }
            }
    }
    public Button getQuadrado( int tagNum){
        return (Button)getView().findViewWithTag(square+tagNum);
    }
    public void finish (View v){
        gameFinished();
        this.player_x=0;
        this.player_o=0;
        eneableQuadrado(false);
        String x =Integer.toString(player_x);
        String o = Integer.toString(player_o);
        getQuadrado(11).setText(x);
        getQuadrado(12).setText(o);

    }
    public void gameFinished(){
        if (player_o > player_x ){
            Toast.makeText(getView().getContext(),"Player O vitorioso. Parabens!",Toast.LENGTH_LONG).show();
        }else {
            if (player_x > player_o ){
                Toast.makeText(getView().getContext(),"Player X vitorioso. Parabens!",Toast.LENGTH_LONG).show();
    }}}
    public View getView(){
        return view;
    }
    public void setView(View view){
        this.view = view;
    }
    public String getLastPlayed(){
        return lastPlayed;
    }
    public void setLastPlayed(String lastPlayed) {
        this.lastPlayed = lastPlayed;
    }
    public void addVitoriaX(){
        this.player_x = player_x+1;
    }
    public void addVitoriaO(){
        this.player_o = player_o+1;
    }
}