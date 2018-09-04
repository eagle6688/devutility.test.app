package devutility.test.app.springmvc;

import devutility.internal.io.DirectoryUtils;

public class PathTest {
	public static void main(String[] args) {
		String projectDirectory = DirectoryUtils.getProjectDirectory();
		System.out.println(projectDirectory);
	}
}