package widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import property.Property;
import youser.Youser;

public class CreateTextField {

	int level;
	Text text;
	GridData gd;
	Property property = Property.getInstance();
	Youser youser = Youser.getInstance();

	public CreateTextField(Composite container){

		level = youser.getLevel();
		gd = new GridData();
		text = new Text(container,SWT.SINGLE);
		text.setText("Level:" + level);
		text.setLayoutData(gd);
	}

	public Text getTextField() {
		return text;
	}

	public void upText() {
		level++;
		text.setText("Level:" + level);
		youser.setLevel(level);
	}
}
