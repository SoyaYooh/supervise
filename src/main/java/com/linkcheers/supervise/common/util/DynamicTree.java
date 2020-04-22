package com.linkcheers.supervise.common.util;

import com.linkcheers.supervise.dto.BaseTree;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Soya
 */
public class DynamicTree {
	/**
	 * 使用递归方法建树
	 * 使用时如果传入对象的子节点名称不是pid需要在对象中覆盖
	 * 栗子:	 parentId为t对象的父类节点名称，可以在构造容器中super.pid = parentId;
	 * @param e
	 * @param p
	 * @return
	 */
	public static <E extends BaseTree<E>> List<E> buildTree(List<E> e, String p) {
		List<E> t = new ArrayList<>();
		e.forEach(a -> {
			if (p.equals(a.getPid())) {
				t.add(findChild(a, e));
			}
		});
		return t;
	}

	/**
	 * 递归查找子节点
	 * 不带条件的
	 * @param e
	 * @param n
	 * @return
	 */
	public static <E extends BaseTree<E>> E findChild(E e, List<E> n) {
		n.forEach(a -> {
			if (e.getId().equals(a.getPid())) {
				if (e.getChild() == null) {
					e.setChild(new ArrayList<>());
				}
				e.getChild().add(findChild(a, n));
			}
		});
		return e;
	}
	/**
	 * 使用递归方法建树
	 * 需要对某些节点剔除，使用时如果传入对象的子节点名称不是pid需要在对象中覆盖
	 * 栗子:	 parentId为t对象的父类节点名称，可以在构造容器中super.pid = parentId;
	 * 并且需要要对t 对象根据业务对isPermit值进行覆盖
	 * @param e
	 * @param p
	 * @return
	 */
	public static <E extends BaseTree<E>> List<E> buildTreeP(List<E> e, String p) {
		List<E> t = new ArrayList<>();
		e.forEach(a -> {
			if (p.equals(a.getPid())&&a.isPermit()) {
				t.add(findChildP(a, e));
			}
		});
		return t;
	}
	/**
	 * 递归查找子节点
	 * 带条件的
	 * @param e
	 * @param n
	 * @return
	 */
	public static <E extends BaseTree<E>> E findChildP(E e, List<E> n) {
		n.forEach(a -> {
			if (e.getId().equals(a.getPid())&&a.isPermit()) {
				if (e.getChild() == null) {
					e.setChild(new ArrayList<>());
				}
				e.getChild().add(findChildP(a, n));
			}
		});
		return e;
	}
	/**
	 * 遍历树，使用时如果传入对象的子节点名称不是pid需要在对象中覆盖
	 * 栗子:	 parentId为t对象的父类节点名称，可以在构造容器中super.pid = parentId;
	 * @param t
	 * @param p
	 * @return
	 */
	@Deprecated
	public static <E extends BaseTree<E>> List<E> bulidTree(List<E> t, String p) {
		List<E> c = t.stream().filter(
				a -> p.equals(a.getPid())
		).collect(Collectors.toList());
		List<E> e = t.stream().filter(
				a -> !p.equals(a.getPid())
		).collect(Collectors.toList());
		c.forEach(a -> {
			bulidTree(e, a.getId()).forEach(
					y -> {
						if (a.getChild() == null) {
							a.setChild(new ArrayList<>());
						}
						a.getChild().add(y);
					}
			);
		});
		return c;
	}

	/**
	 * 需要对某些节点剔除，使用时如果传入对象的子节点名称不是pid需要在对象中覆盖
	 * 栗子:	 parentId为t对象的父类节点名称，可以在构造容器中super.pid = parentId;
	 * 并且需要要对t 对象根据业务对isPermit值进行覆盖
	 * @param t
	 * @param p
	 * @return
	 */
	@Deprecated
	public static <E extends BaseTree<E>> List<E> bulidTreed(List<E> t, String p) {
		List<E> c = t.stream().filter(a -> p.equals(a.getPid())&& a.isPermit()
		).collect(Collectors.toList());
		List<E> e = t.stream().filter(
				a -> !p.equals(a.getPid())
		).collect(Collectors.toList());
		c.forEach(a -> {
			bulidTree(e, a.getId()).forEach(
					y -> {
						if (a.getChild() == null) {
							a.setChild(new ArrayList<>());
						}
						if(y.isPermit()){
							a.getChild().add(y);
						}
					}
			);
		});
		return c;
	}
}
