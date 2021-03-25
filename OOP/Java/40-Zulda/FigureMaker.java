import ea.*;
import ea.internal.gra.PixelFeld;
import ea.internal.io.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FigureMaker {

    public static Figur makeFigureFromSprite( String pPath, int pWidth ) {
        return makeFigureFromSprite(pPath,  pWidth, pWidth, 0, 0);
    }

    public static Figur makeFigureFromSprite( String pPath, int pWidth, int pHeight, int pRow, int pSpriteCount ) {
        BufferedImage image = ImageLoader.loadExternalImage(pPath);

        int spriteCount = pSpriteCount;
        if( pSpriteCount <= 1 ) {
            spriteCount = image.getWidth() / pWidth;
        }

        PixelFeld[] pfelder = new PixelFeld[spriteCount];

        for ( int s = 0; s < spriteCount; s++ ) {
            pfelder[s] = new PixelFeld(pWidth, pHeight, 1);
            for (int x = 0; x < pWidth; x++) {
                for (int y = 0; y < pHeight; y++) {
                    Color clr = new Color(image.getRGB(
                        (s*pWidth+x),
                        (pRow*pHeight+y)
                    ), true);
                    if( clr.getAlpha() > 0 ) {
                        pfelder[s].farbeSetzen(x, y, clr);
                    }
                }
            }
        }

        Figur fig = new Figur();
        fig.animationSetzen(pfelder);
        return fig;
    }

    public static void main(String[] args) {
        String[] states = new String[]{
            "idle_right", "run_right", "slashup_right", "slashdown_right", "slash_right", "jump_right", "hit_right", "die_right",
            "idle_left", "run_left", "slashup_left", "slashdown_left", "slash_left", "jump_left", "hit_left", "die_left"
        };
        int[] sprite_counts = new int[]{
            13, 8, 10, 10, 10, 6, 4, 7,
            13, 8, 10, 10, 10, 6, 4, 7
        };
        for( int i = 0; i < states.length; i++ ) {
            Figur fig = FigureMaker.makeFigureFromSprite("/Users/jneug/Projekte/Schule/schule-projekte/OOP/BlueJ/40-Zulda/images/adventurer.png", 32, 32, i, sprite_counts[i]);
            DateiManager.schreiben(fig, "/Users/jneug/Projekte/Schule/schule-projekte/OOP/BlueJ/40-Zulda/images/adventurer_"+states[i]+".eaf");
            System.out.println("Wrote figure for state "+states[i]);
        }
        System.out.println("Success!");
        System.exit(0);
    }

}
