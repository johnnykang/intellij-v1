import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class MainComponent implements ApplicationComponent, Configurable {

    public MainComponent() {
        super();
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "V1";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        return new JPanel();
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public void apply() throws ConfigurationException {

    }


//    public void actionPerformed(AnActionEvent event) {
//        Project project = event.getProject();
//        Messages.showMessageDialog(project, "Hello world!", "Greeting", Messages.getInformationIcon());
//    }
}
