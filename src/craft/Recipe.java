package craft;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

public class Recipe {
	private static Map<String, Craft> craft = new HashMap<String, Craft>();

	public static void addRecipe(Plugin plugin, Stack stack, Stack[] istack) {
		if (stack == null) {
			throw new IllegalArgumentException("Result cannot be null.");
		}
		ShapedRecipe rc = new ShapedRecipe(new NamespacedKey(plugin, UUID.randomUUID().toString()), stack.getStack());
		rc.shape("012", "345", "678");
		Craft cr = new Craft();
		cr.add(stack.getStack());
		for (int i = 0; i < 9; i++) {
			if (istack[i] != null && istack[i].getStack().getType() != Material.AIR) {
				rc.setIngredient(String.valueOf(i).toCharArray()[0], istack[i].getStack().getType());
				cr.add(istack[i].getStack());
			} else {
				cr.add(null);
			}
		}
		Bukkit.getServer().addRecipe(rc);
		craft.put(rc.getKey().getKey(), cr);
	}

	public static Craft getRecipe(String key) {
		return craft.get(key);
	}

	public static Boolean hasRecipe(String key) {
		return craft.containsKey(key);
	}
}