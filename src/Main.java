import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter{
    public static void main(String[] args) {

        JDABuilder jdaBuilder = JDABuilder.createDefault("OTc4ODIzNDIwMzY2MTU5OTAy.G_6jRn.SsXeAyelZKNQo-cIPL-ENz0ocjLpdcrWXT2-3I");
            jdaBuilder.addEventListeners(new Commands());
            try{
                jdaBuilder.build();
            } catch (LoginException e){
                e.printStackTrace();
            }
            //try{
            //    jda.awaitReady();
            //} catch (InterruptedException e){
            //    e.printStackTrace();
            //}


    }
}