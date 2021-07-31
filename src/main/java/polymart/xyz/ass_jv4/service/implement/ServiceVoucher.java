package polymart.xyz.ass_jv4.service.implement;

import polymart.xyz.ass_jv4.dao.IDAOVoucher;
import polymart.xyz.ass_jv4.dao.implement.DAOVoucher;
import polymart.xyz.ass_jv4.entity.EntityVoucher;
import polymart.xyz.ass_jv4.service.IServiceVoucher;

import java.util.List;

public class ServiceVoucher implements IServiceVoucher {

    private IDAOVoucher _idaoVoucher = new DAOVoucher();

    @Override
    public List<EntityVoucher> findAll() {
        return _idaoVoucher.findAll();
    }

    @Override
    public EntityVoucher findById(String id) {
        return _idaoVoucher.findById(id);
    }

    @Override
    public boolean newVoucher(EntityVoucher entityVoucher) {
        return _idaoVoucher.newVoucher(entityVoucher);
    }

    @Override
    public boolean updateVoucher(EntityVoucher entityVoucher) {
        return _idaoVoucher.updateVoucher(entityVoucher);
    }
}
