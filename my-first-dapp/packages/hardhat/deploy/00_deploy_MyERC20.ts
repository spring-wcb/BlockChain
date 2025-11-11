import { HardhatRuntimeEnvironment } from "hardhat/types";
import { DeployFunction } from "hardhat-deploy/types";

const func: DeployFunction = async function (hre: HardhatRuntimeEnvironment) {
    const { deployer } = await hre.getNamedAccounts();
    const { deploy } = hre.deployments;

    // 部署合约
    await deploy("MyERC20", {
        from: deployer,
        args: [], // 你的合约构造函数没有参数
        log: true,
        autoMine: true,
    });

    const MyERC20 = await hre.ethers.getContract("MyERC20", deployer);
};

export default func;
func.tags = ["ERC20WCB202392451121"];