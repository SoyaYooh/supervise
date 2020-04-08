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
	 *
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
	 *
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
	 * lambda
	 *
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
}
