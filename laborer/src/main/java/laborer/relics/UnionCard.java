package laborer.relics;


import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ThornsPower;
import laborer.LaborerMod;
import laborer.util.TextureLoader;

import static laborer.LaborerMod.makeRelicOutlinePath;
import static laborer.LaborerMod.makeRelicPath;

public class UnionCard extends CustomRelic {
  public static final String ID = LaborerMod.makeID("UnionCard");
  private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
  private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));

  public UnionCard() {
    super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.SOLID);
  }

  @Override
  public void atBattleStart() {
    this.flash();
    AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(
        AbstractDungeon.player, AbstractDungeon.player, new ThornsPower(AbstractDungeon.player, 3), 3
    ));
  }
}
