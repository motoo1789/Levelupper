package levelupper3;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import widgets.CreateProgressBar;
import widgets.CreateTextField;

public class View extends ViewPart {

	//widget
		Composite container;
		ProgressBar probar;
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();

		//生成用のオブジェクト
		CreateProgressBar progressObject;
		CreateTextField textObject;

		public View() {

		}

		@Override
		public void createPartControl(Composite parent) {


			container = new Composite(parent,SWT.NONE);
			container.setLayout(new GridLayout(1,true));

			//プログレスバー
			progressObject = new CreateProgressBar(container);
			probar = progressObject.getProgressBar();

			//テキストフィールド
			textObject = new CreateTextField(container);


			Shell shell = workbenchWindow.getShell();
			shell.getDisplay().addFilter(SWT.KeyDown, new Listener()
			{

				@Override
				public void handleEvent(Event event) {
					// TODO 自動生成されたメソッド・スタブ
					System.out.println("キー入力された");
					progressObject.upBar();
				}

			});

		}

		@Override
		public void setFocus() {
			// TODO 自動生成されたメソッド・スタブ

		}

		public void upText() {
			// TODO 自動生成されたメソッド・スタブ
			textObject.upText();
		}

		public int getNextLevel() {
			return progressObject.getNextLevel();
		}

		public int getBarNum() {
			return progressObject.getBarNum();
		}

}
