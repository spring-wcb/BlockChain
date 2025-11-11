// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract MyERC20 {
    // 代币基本信息
    string public name;
    string public symbol;
    uint8 public decimals;
    uint256 public totalSupply;

    // 代币所有者（部署者）
    address public owner;

    // 余额映射 - 编译器会自动生成 balanceOf(address) 函数
    mapping(address => uint256) public balanceOf;

    // 授权映射 - 编译器会自动生成 allowance(address,address) 函数
    mapping(address => mapping(address => uint256)) public allowance;

    // 事件
    event Transfer(address indexed from, address indexed to, uint256 value);
    event Approval(address indexed owner, address indexed spender, uint256 value);

    // 构造函数 - 使用你的名字拼音缩写
    constructor() {
        name = "WCBToken";  // 替换为你的名字拼音缩写
        symbol = "WCB";     // 替换为你的名字缩写
        decimals = 18;
        totalSupply = 0;
        owner = msg.sender;
    }

    // 转账函数
    function transfer(address _to, uint256 _value) public returns (bool success) {
        require(balanceOf[msg.sender] >= _value, "Insufficient balance");
        balanceOf[msg.sender] -= _value;
        balanceOf[_to] += _value;
        emit Transfer(msg.sender, _to, _value);
        return true;
    }

    // 授权转账函数
    function transferFrom(address _from, address _to, uint256 _value) public returns (bool success) {
        require(balanceOf[_from] >= _value, "Insufficient balance");
        require(allowance[_from][msg.sender] >= _value, "Allowance exceeded");

        balanceOf[_from] -= _value;
        balanceOf[_to] += _value;
        allowance[_from][msg.sender] -= _value;

        emit Transfer(_from, _to, _value);
        return true;
    }

    // 授权函数
    function approve(address _spender, uint256 _value) public returns (bool success) {
        allowance[msg.sender][_spender] = _value;
        emit Approval(msg.sender, _spender, _value);
        return true;
    }

    // 发行代币（只有所有者可以调用）
    function mint(uint256 _amount) public returns (bool) {
        require(msg.sender == owner, "Only owner can mint");
        balanceOf[msg.sender] += _amount;
        totalSupply += _amount;
        emit Transfer(address(0), msg.sender, _amount);
        return true;
    }

    // 销毁代币
    function burn(uint256 _amount) public {
        require(balanceOf[msg.sender] >= _amount, "Insufficient balance");
        balanceOf[msg.sender] -= _amount;
        totalSupply -= _amount;
        emit Transfer(msg.sender, address(0), _amount);
    }
}