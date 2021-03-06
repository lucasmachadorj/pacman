package pacman;

import java.util.List;
import prolog.Logica;
import prolog.Position;

public class IntelFraca implements Inteligencia {

    private int x;
    private int y;
    private int iter;
    private final int codGhost;
    private final Logica logica;
    private final int sleep;
    List<Position> pos;
    
    IntelFraca(Logica logica,int codGhost,int sleep)
    {
        this.logica=logica;
        this.codGhost = codGhost;
        logica.criarFantasma(codGhost);
        x=logica.FantasmaX(codGhost);
        y=logica.FantasmaY(codGhost);
        iter=0;
        this.sleep =sleep ;
        pos= logica.getPath(x , y);
    }
    
    
    @Override
    public void proximoMovimento() 
    {
        
        if(iter<pos.size()){
           
            int x1 = pos.get(iter).getX();
            int y1 = pos.get(iter).getY();
                
            if(x1==x+1 && y1==y)
            {
                logica.rightFantasma(codGhost);
            }
            else
                if(x1==x-1 && y1==y)
                {
                    logica.leftFantasma(codGhost);
                }
                else
                    if(x1==x && y1==y+1)
                    {
                        logica.downFantasma(codGhost);
                    }
                    else
                       if(x1==x && y1==y-1)
                       {
                            logica.upFantasma(codGhost); 
                       }
            
            x=logica.FantasmaX(codGhost);
            y=logica.FantasmaY(codGhost);
                      
            iter++;
            
        }
       
        
        if(iter==pos.size()-1){
            iter=0;
            pos=logica.getPath2(x , y);
        }      
             
    }
    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getSleep() {
       return sleep;
    }
    
}
