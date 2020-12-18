package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;

import youser.Youser;

public class SQLite {

	private String databaseName = "\\Levelupper.sqlite3";
	private String projectPath = "C:\\pleiades\\workspace\\sqlite\\sqlite";
	private static Connection conn = null;
    private static Statement statement = null;
    public static SQLite sqlite = new SQLite();

    private SQLite() {

    	try {

//       	IWorkbench workbench = PlatformUI.getWorkbench();
//    		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
//    		IEditorPart editor = window.getActivePage().getActiveEditor();
//    		IEditorInput editorInput = editor.getEditorInput();
//    		IFile file = editorInput.getAdapter(IFile.class);
//    		IProject project = file.getProject();
//    		projectPath = project.getLocation().toOSString();

    		IWorkspace workspace = ResourcesPlugin.getWorkspace();
    		IWorkspaceRoot workspaceRoot = workspace.getRoot();
    		projectPath = workspaceRoot.getLocation().toOSString();
    		System.out.println("データベースの場所" + projectPath);

			conn = DriverManager.getConnection("jdbc:sqlite:" + projectPath + databaseName);
			statement = conn.createStatement();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

    }

    public static SQLite getInstanse() {
    	return sqlite;
    }

    public boolean hasDB() {

    	boolean flag = true;
    	String query = "SELECT COUNT(*) FROM sqlite_master WHERE type='table' AND name='Youser';";
    	try {
			ResultSet rs = statement.executeQuery(query);

			System.out.println(rs.getBoolean(1));
			if(rs.getBoolean(1) == true)
			{
				flag = true;
			}
			else
			{
				flag = false;
			}

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return flag;

    }

    public static ArrayList<Integer> readDB() {

    	ArrayList<Integer> list = new ArrayList<Integer>();
		String selectqyery = "select * from Youser;";

		try {
			ResultSet reYouser = statement.executeQuery(selectqyery);
			while(reYouser.next())
			{
				list.add(reYouser.getInt("Level"));
				list.add(reYouser.getInt("nextLevel"));
				list.add(reYouser.getInt("barnum"));
			}

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return list;

    }

    public static void stopDB() {

    	String query = "SELECT COUNT(*) FROM sqlite_master WHERE type='table' AND name='Youser';";
    	try {
			ResultSet rs = statement.executeQuery(query);

			System.out.println(rs.getBoolean(1));
			if(rs.getBoolean(1) == true)
			{
				updataYouser();
			}
			else
			{
				System.out.println("テーブルがない");
				createTable();
				updataYouser();
			}

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

    }

    private static void createTable() {
    	String createtable = "create table Youser(id integer,Level integer, nextLevel integer, barnum integer); ";
    	String insertData = "insert into Youser values(1, 1, 10, 0); ";
    	try {
			statement.execute(createtable);
			statement.execute(insertData);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
    }

    private static void updataYouser() {

    	int Level,nextLevel,barnum;
    	Level = nextLevel = barnum = 0;

    	Youser youser = Youser.getInstance();
    	Level +=  youser.getLevel();
    	nextLevel += youser.getnextLevel();
    	barnum += youser.getBarnum();

    	final String SQL = "update Youser set Level = ? ,nextLevel = ? ,barnum = ? where id = ?";


    	try {
			conn.setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

        try(PreparedStatement ps = conn.prepareStatement(SQL)){
            ps.setInt(1,Level);
            ps.setInt(2, nextLevel);
            ps.setInt(3, barnum);
            ps.setInt(4, 1);

            ps.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
            System.out.println("rollback");
            try {
				throw e;
			} catch (Exception e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
        }
    }
}
