package com.tttiger.admin.service.sys.iml;

import com.tttiger.admin.bean.sys.Dictionary;
import com.tttiger.admin.common.ResultMap;
import com.tttiger.admin.service.sys.ApplicationConfigService;
import com.tttiger.admin.service.sys.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author 秦浩桐
 * @version 1.0
 * @date 2021/03/10 20:16
 */
@Service
public class ApplicationConfigServiceImpl implements ApplicationConfigService, ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private DictionaryService dictionaryService;

    private volatile boolean loading = true;

    private Map<UniteDicKey, List<Dictionary>> selectWithModuleGroupDic = new HashMap<>();

    @Override
    public List<Dictionary> getConfig(String moduleName, String groupName, String dicKey) {
        while (this.loading){
            Thread.yield();
        }
        UniteDicKey uniteDicKey = new UniteDicKey(moduleName,groupName,dicKey);
        return selectWithModuleGroupDic.get(uniteDicKey);
    }

    @Override
    public Dictionary getSingleConfig(String moduleName, String groupName, String dicKey) {
        while (this.loading){
            Thread.yield();
        }
        UniteDicKey uniteDicKey = new UniteDicKey(moduleName,groupName,dicKey);
        List<Dictionary> dictionaries = selectWithModuleGroupDic.get(uniteDicKey);
        return dictionaries.get(0);
    }

    @Override
    public void reloadConfig() {
        this.loading = true;
        this.selectWithModuleGroupDic = null;
        loadConfig();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadConfig();
    }

    private void loadConfig(){
        this.loading = true;
        ResultMap<List<Dictionary>> listResultMap = dictionaryService.selectList(null);
        listResultMap.getData().forEach(x -> {
            UniteDicKey uniteDicKey = new UniteDicKey(x.getModuleName(),x.getGroupName(),x.getDicKey());
            if (selectWithModuleGroupDic.containsKey(uniteDicKey)) {
                selectWithModuleGroupDic.get(uniteDicKey).add(x);
            } else {
                List<Dictionary> list = new ArrayList<>();
                list.add(x);
                selectWithModuleGroupDic.put(uniteDicKey, list);
            }
        });
        this.loading = false;
    }

    class UniteDicKey {
        private String moduleName;
        private String groupName;
        private String dicKey;

        public String getModuleName() {
            return moduleName;
        }

        public void setModuleName(String moduleName) {
            this.moduleName = moduleName;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public String getDicKey() {
            return dicKey;
        }

        public void setDicKey(String dicKey) {
            this.dicKey = dicKey;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            UniteDicKey that = (UniteDicKey) o;
            return Objects.equals(moduleName, that.moduleName) &&
                    Objects.equals(groupName, that.groupName) &&
                    Objects.equals(dicKey, that.dicKey);
        }

        @Override
        public int hashCode() {

            return Objects.hash(moduleName, groupName, dicKey);
        }

        private UniteDicKey(){}

        public UniteDicKey(String moduleName, String groupName, String dicKey) {
            this.moduleName = moduleName;
            this.groupName = groupName;
            this.dicKey = dicKey;
        }
    }
}
