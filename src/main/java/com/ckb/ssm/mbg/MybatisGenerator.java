package com.ckb.ssm.mbg;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用于生产MBG的代码
 * @author ychen
 */
public class MybatisGenerator {
    public static void main(String[] args) throws Exception {
        {
            String today = "2020-11-26";

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date now = sdf.parse(today);
            Date d = new Date();

            if (d.getTime() > now.getTime() + 1000 * 60 * 60 * 24) {
                System.err.println("――――――未成成功运行――――――");
                System.err.println("――――――未成成功运行――――――");
                System.err.println("本程序具有破坏作用，应该只运行一次，如果必须要再运行，需要修改today变量为今天，如:" + sdf.format(new Date()));
                return;
            }

            if (false)
                return;
            //MBG 执行过程中的警告信息
            List<String> warnings = new ArrayList<String>();
            //当生成的代码重复时，覆盖原代码
            boolean overwrite = true;
            //读取我们的 MBG 配置文件
            InputStream is = MybatisGenerator.class.getClassLoader().getResource("generatorConfig.xml").openStream();
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(is);
            is.close();
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            //创建 MBG
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            //执行生成代码
            myBatisGenerator.generate(null);
            //输出警告信息
            for (String warning : warnings) {
                System.out.println(warning);
            }
        }
    }
}