package t3;

import java.lang.reflect.Method;

public class Test {

	public static void main(String[] args) {
		NoBug no = new NoBug();
		Class<? extends NoBug> clazz = no.getClass();
		Method[] method = clazz.getMethods();
		StringBuilder log = new StringBuilder();
		int errornum = 0 ;
		for(Method m : method) {
			if(m.isAnnotationPresent(Jiecha.class)) {
				try {
					m.setAccessible(true);
					m.invoke(no, null);
				}catch (Exception e) {
					errornum++;
                    log.append(m.getName());
                    log.append(" ");
                    log.append("has error:");
                    log.append("\n\r  caused by ");
                    //记录测试过程中，发生的异常的名称
                    log.append(e.getCause().getClass().getSimpleName());
                    log.append("\n\r");
                    //记录测试过程中，发生的异常的具体信息
                    log.append(e.getCause().getMessage());
                    log.append("\n\r");
				}
			}
		}
		
		log.append(clazz.getSimpleName());
        log.append(" has  ");
        log.append(errornum);
        log.append(" error.");

        // 生成测试报告
        System.out.println(log.toString());
		
	}

}
