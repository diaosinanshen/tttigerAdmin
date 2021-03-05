package com.tttiger.admin.service.sys.iml;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tttiger.admin.bean.sys.Dictionary;
import com.tttiger.admin.mapper.sys.DictionaryMapper;
import com.tttiger.admin.service.sys.DictionaryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author QinHaoTong
 * @dateTime 2020/12/23 9:29
 * @description
 */
@Service
@AllArgsConstructor
public class DictionaryServiceImpl implements DictionaryService {

    private DictionaryMapper dictionaryMapper;

    @Override
    public BaseMapper<Dictionary> getMapper() {
        return dictionaryMapper;
    }
}
