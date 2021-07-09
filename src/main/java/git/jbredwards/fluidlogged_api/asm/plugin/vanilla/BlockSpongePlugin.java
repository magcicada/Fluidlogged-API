package git.jbredwards.fluidlogged_api.asm.plugin.vanilla;

import git.jbredwards.fluidlogged_api.asm.ASMUtils;
import git.jbredwards.fluidlogged_api.asm.AbstractPlugin;
import org.objectweb.asm.tree.*;

import javax.annotation.Nullable;

/**
 * sponges now drain fluids properly
 * @author jbred
 *
 */
public final class BlockSpongePlugin extends AbstractPlugin
{
    @Nullable
    @Override
    public String getMethodName(boolean obfuscated) {
        return obfuscated ? "func_176311_e" : "tryAbsorb";
    }

    @Nullable
    @Override
    public String getMethodDesc() {
        return "(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/state/IBlockState;)V";
    }

    @Override
    public boolean transform(InsnList instructions, MethodNode method, AbstractInsnNode insn, boolean obfuscated) {
        if(insn.getOpcode() == INVOKESPECIAL && ASMUtils.checkMethod(insn, obfuscated ? "func_176312_d" : "absorb", null)) {
            instructions.insert(insn, new MethodInsnNode(INVOKESTATIC, "git/jbredwards/fluidlogged_api/asm/ASMHooks", "absorb", "(Lnet/minecraft/block/BlockSponge;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)Z", false));
            instructions.remove(insn);

            return true;
        }

        return false;
    }
}
