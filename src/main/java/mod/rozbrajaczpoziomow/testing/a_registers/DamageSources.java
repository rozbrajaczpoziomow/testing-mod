package mod.rozbrajaczpoziomow.testing.a_registers;

import net.minecraft.util.DamageSource;

public class DamageSources {
	public static final DamageSource NIGHTMARES = new DamageSource("nightmares").bypassMagic().bypassInvul().bypassArmor();
	public static final DamageSource TIMER = new DamageSource("timer").bypassMagic().bypassInvul().bypassArmor();
}
