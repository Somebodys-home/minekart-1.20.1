package net.gabriel.minekart.entity.animation;

import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;

public class ModAnimations {
    public static final Animation RETICLE_IDLE = Animation.Builder.create(0.875f)
            .addBoneAnimation("impact",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.625f, AnimationHelper.createTranslationalVector(0f, -1f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.6766666f, AnimationHelper.createTranslationalVector(0f, -1f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.75f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.7916766f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.8343334f, AnimationHelper.createTranslationalVector(0f, -1f, 0f),
                                    Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("impact",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.6766666f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("impact",
                    new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.625f, AnimationHelper.createScalingVector(0f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.6766666f, AnimationHelper.createScalingVector(0.5f, 1.1f, 0.5f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.875f, AnimationHelper.createScalingVector(1.75f, 0f, 1.75f),
                                    Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("strike",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.5416766f, AnimationHelper.createTranslationalVector(0f, 30f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.625f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("strike",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.5834334f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.7916766f, AnimationHelper.createRotationalVector(0f, 90f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("strike",
                    new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.5834334f, AnimationHelper.createScalingVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.625f, AnimationHelper.createScalingVector(1f, 4f, 1f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.7083434f, AnimationHelper.createScalingVector(3f, 6f, 3f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.7916766f, AnimationHelper.createScalingVector(0f, 12f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("circle",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.6766666f, AnimationHelper.createRotationalVector(0f, 360f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("circle",
                    new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0f, AnimationHelper.createScalingVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.125f, AnimationHelper.createScalingVector(1.6f, 1.6f, 1.6f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5416766f, AnimationHelper.createScalingVector(1.6f, 1.6f, 1.6f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.6766666f, AnimationHelper.createScalingVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR))).build();
}
