package com.multicampus.biz.main.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multicampus.biz.common.DBManagerException;
import com.multicampus.biz.common.EntityObject;
import com.multicampus.biz.common.ListObject;
import com.multicampus.biz.main.MainService;
import com.multicampus.biz.main.vo.MainVO;

@Service("mainService")
public class MainServiceImpl implements MainService {
	@Autowired
	private MainDAOIbatis mainDAO;
	
	public MainVO getMain(MainVO vo) {
		return mainDAO.getMain(vo);
	}

	public ListObject goMainView(MainVO vo) throws DBManagerException {
		
		//�񽺴Ͻ� ������ ���� �� �κ� EJB�� ������ SesssionBean�� ����
		ListObject list = null;

		EntityObject metaInEo = null;
		EntityObject metaOutEo = null;

		ArrayList<MainVO> firstMenuLo = null;
		ListObject secondMenuLo1 = null;
		ListObject secondMenuLo = null;
		ArrayList<MainVO> secondMenuLoAll = null;

		EntityObject tempEo = null;

        try {
			//��ȸ
			list = new ListObject();
			
			//1�ܰ� �޴�
			vo.setDOCUMENT_NO("1");
			firstMenuLo = (ArrayList<MainVO>)mainDAO.findMyMenu( vo );
			list.add(firstMenuLo);

			//2�ܰ� �޴�
			String currentParentMenuId = "";
			String previousParentMenuId = "";
			int size = 0;
			for (int j=1;j<2;j++)
			{
				metaInEo = new EntityObject();
				vo.setDOCUMENT_NO(String.valueOf( j + 1 ));
				secondMenuLoAll = (ArrayList<MainVO>)mainDAO.findMyMenu( vo );
				secondMenuLo1 = new ListObject();
				secondMenuLo1.add(secondMenuLoAll);
				tempEo = new EntityObject();
				metaOutEo = new EntityObject();
				secondMenuLo = new ListObject();

				currentParentMenuId = "";
				previousParentMenuId = "";

				size = secondMenuLoAll.size();
				for (int i=0;i<size;i++ ){
					tempEo = (EntityObject)secondMenuLo1.get(i);
					currentParentMenuId = (String)tempEo.get("FK_CD_TP");

					if (!currentParentMenuId.equals(previousParentMenuId))  // ���������� �Ǿ�����,
					{
						if (i!=0)
						{   // ���������� ������. (�������� ����)
							metaOutEo.put( previousParentMenuId, secondMenuLo );
						}
				 		secondMenuLo = new ListObject();
					}
					secondMenuLo.add (tempEo);

					previousParentMenuId = currentParentMenuId;
				}
				metaOutEo.put( previousParentMenuId, secondMenuLo );
				list.add(metaOutEo);

			}
			
			//����� ���� �߰�
/*			EntityObject userEo = new EntityObject();
			userEo = mainDAO.getUserByUserId(vo);
			userEo.put("userName", vo.getEMPLOYEE_NAME());
			list.add(userEo);*/
			
			return list;
        }
		catch(Exception ee) {
			throw new DBManagerException(ee.getMessage());
		}
        finally {
        }
	}
}

