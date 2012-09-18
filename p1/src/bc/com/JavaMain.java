package bc.com;

class ThreadA extends Thread {
	int count = 0;

	public void run() {
		System.out.println(getName() + " 将要运行...");
		while (!this.isInterrupted()) {
			System.out.println(getName() + " 运行中 " + count++);
			try {
				Thread.sleep(400); // 休眠400毫秒
			} catch (InterruptedException e) { // 退出阻塞态时将捕获异常
				System.out.println(getName() + "从阻塞态中退出...");
				this.interrupt(); // 改变线程状态，使循环结束, 此处interrupt使线程结束
			}
		}
		System.out.println(getName() + " 已经终止！");
	}
}

class JavaMain1 {
	public static void main(String argv[]) throws InterruptedException {

		regularTest();
	}

	private static void regularTest() {
		String temp = " -- _a  a";
		if (temp.contains(" ") || temp.contains("-") || temp.contains("_")) {
			System.out.println(temp.replaceAll("-|\\s|_", ""));
		}

	}

	private void threadTest() throws InterruptedException {
		ThreadA ta = new ThreadA();
		ta.setName("ThreadA");
		ta.start();
		Thread.sleep(4000);// 主线程休眠2000毫秒，等待其他线程执行
		System.out.println(ta.getName() + " 正在被中断.....");
		ta.interrupt(); // 中断线程ThreadA
	}
}