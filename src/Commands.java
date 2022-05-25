import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().equalsIgnoreCase("!goha")) {
            event.getChannel().sendMessage("3 zł").queue();
        }
        if (event.getMessage().getContentRaw().equalsIgnoreCase("!tip")) {
            Tips tips = new Tips();
            event.getChannel().sendMessage(tips.rollRandomTip()).queue();
        }
        if (event.getMessage().getContentRaw().equalsIgnoreCase("!weather")) {
            Weather weather = new Weather();
            event.getChannel().sendMessage(weather.checkWeather()).queue();
        }
    }
}
