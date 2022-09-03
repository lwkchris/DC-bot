import event.Translator;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Main {
    public static void main(String[] args) throws Exception {
        JDA jda = JDABuilder.createDefault("MTAxNDM3MzQyOTAzOTg2NTg2Ng.GL0SfZ.qBkvA0VIUC5nAqHk_qKSrdPYQ6PgCMHmh3yS-w")
                .build();

        jda.addEventListener(new Translator());
    }
}
