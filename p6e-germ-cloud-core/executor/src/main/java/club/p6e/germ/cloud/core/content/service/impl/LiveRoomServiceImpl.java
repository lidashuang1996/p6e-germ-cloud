package club.p6e.germ.cloud.core.content.service.impl;

import club.p6e.germ.cloud.core.content.program.DouYuProgram;
import club.p6e.germ.cloud.core.content.program.HuYaProgram;
import club.p6e.germ.cloud.core.content.service.LiveRoomService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
@Service
public class LiveRoomServiceImpl implements LiveRoomService {

    @Override
    public List<String> list() {
        final List<String> result = new ArrayList<>();
        result.addAll(DouYuProgram.get().keySet());
        result.addAll(HuYaProgram.get().keySet());
        return result;
    }

    @Override
    public List<String> list(String type) {
        if (type == null) {
            return list();
        }
        switch (type) {
            case DOUYU_TYPE:
                return new ArrayList<>(DouYuProgram.get().keySet());
            case HUYA_TYPE:
                return new ArrayList<>(HuYaProgram.get().keySet());
        }
        return new ArrayList<>();
    }

    @Override
    public void create(String type, String rid) {
        switch (type) {
            case DOUYU_TYPE:
                DouYuProgram.create(rid);
                return;
            case HUYA_TYPE:
                HuYaProgram.create(rid);
        }
    }

    @Override
    public void delete(String type, String rid) {
        switch (type) {
            case DOUYU_TYPE:
                DouYuProgram.delete(rid);
                return;
            case HUYA_TYPE:
                HuYaProgram.delete(rid);
        }
    }

    @Override
    public void restart(String type, String rid) {
        switch (type) {
            case DOUYU_TYPE:
                DouYuProgram.restart(rid);
                return;
            case HUYA_TYPE:
                HuYaProgram.restart(rid);
        }
    }
}
